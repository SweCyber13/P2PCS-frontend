#ifndef WIDGET_H
#define WIDGET_H

#include <QtGui/QWidget>
#include<QLabel>
#include<QLineEdit>
#include<QPushButton>
#include<QHBoxLayout>
#include<QVBoxLayout>
#include <QDebug>

class Widget : public QWidget
{
    Q_OBJECT
    
public:
    Widget(QWidget *parent = 0);
    ~Widget();
private :

    QLabel *title;
    QLineEdit *screen1;
    QPushButton *a,*b,*c,*d,*e,*f,*g,*h,*i,*j,*k,*l,*m,*n,*o,*p,*q,*r,*s,*t;
    QHBoxLayout *h1,*h2,*h3,*h4;
    QVBoxLayout *v1;
    v1->event(keyWW(new QKeyEvent));

    bool keyWW(QEvent *a){

    }

private slots :
    void func1();
    void func2();
    void func3();
    void func4();
    void func5();
    void func6();
    void func7();
    void func8();
    void func9();
    void func0();
    void funcdeci();
    void funceql();
    void funcadd();
    void funcmul();
    void funcsub();
    void funcdiv();
    void funcmod();
    void funcon();
    void funcoff();
    void funcclr();



};

#endif // WIDGET_H
