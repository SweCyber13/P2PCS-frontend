<?php

namespace App\Bridge;

use Aws\CognitoIdentityProvider\CognitoIdentityProviderClient;
use Aws\Result;
class AwsCognitoClient
{
    private $client;

    private $poolId;

    private $clientId;

    public function __construct(
        string $poolId,
        string $clientId,
        string $region = 'eu-west-2',
        string $version = 'latest'
    ) {
        $this->client = CognitoIdentityProviderClient::factory([
            'region' => $region,
            'version' => $version
        ]);
        $this->poolId = $poolId;
        $this->clientId = $clientId;
    }

    public function findByUsername(string $username): Result
    {
        return $this->client->listUsers([
            'UserPoolId' => $this->poolId,
            'Filter'     => "email=\"" . $username . "\""
        ]);
    }

    public function checkCredentials($username, $password): Result
    {
        return $this->client->adminInitiateAuth([
            'UserPoolId'     => $this->poolId,
            'ClientId'       => $this->clientId,
            'AuthFlow'       => 'ADMIN_NO_SRP_AUTH', // this matches the 'server-based sign-in' checkbox setting from earlier
            'AuthParameters' => [
                'USERNAME' => $username,
                'PASSWORD' => $password
            ]
        ]);
    }
}

