package com.example.p2pcs_app.login_register_cognito

import android.content.Context
import com.amazonaws.AmazonClientException
import com.amazonaws.mobileconnectors.cognitoidentityprovider.*
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.*
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.cognitoidentityprovider.model.*

class CognitoSettings(val context: Context) {
    private val userPoolId: String = "us-east-1_GalreFteo"
    private val clientId: String = "7i90ntqhvcktikqjcs5btg8jb9"
    private val clientSecret: String = "1rpfr80nh7bhp0s8do8svnba6c1spvmuoisgde9sib6fsm9rqnjs"
    private val cognitoRegion: Regions = Regions.US_EAST_1
    //getter and setter methods are auto generated userpoolid() returns the string
    fun getUserPool(): CognitoUserPool{
        return CognitoUserPool(context, userPoolId, clientId, clientSecret ,cognitoRegion)
    }

}