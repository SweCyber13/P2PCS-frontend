#include "widget.h"
float varA,varB,result;
int z=0,varC,varD;

Widget::Widget(QWidget *parent)
     : QWidget(parent)
{
    title = new QLabel("ABRAAR");
    screen1 = new QLineEdit;
    a = new QPushButton("1");
    b = new QPushButton("2");
    c = new QPushButton("3");
    d = new QPushButton("ON");
    e = new QPushButton("OFF");
    f = new QPushButton("4");
    g = new QPushButton("5");
    h = new QPushButton("6");
    i = new QPushButton("+");
    j = new QPushButton("-");
    k = new QPushButton("7");
    l = new QPushButton("8");
    m = new QPushButton("9");
    n = new QPushButton("*");
    o = new QPushButton("/");
    p = new QPushButton(".");
    q = new QPushButton("0");
    r = new QPushButton("=");
    s = new QPushButton("%");
    t = new QPushButton("CLEAR");
    h1 = new QHBoxLayout();
    h1->addWidget(a);
    h1->addWidget(b);
    h1->addWidget(c);
    h1->addWidget(d);
    h1->addWidget(e);
    h2 = new QHBoxLayout();
    h2->addWidget(f);
    h2->addWidget(g);
    h2->addWidget(h);
    h2->addWidget(i);
    h2->addWidget(j);
    h3 = new QHBoxLayout();
    h3->addWidget(k);
    h3->addWidget(l);
    h3->addWidget(m);
    h3->addWidget(n);
    h3->addWidget(o);
    h4 = new QHBoxLayout();
    h4->addWidget(p);
    h4->addWidget(q);
    h4->addWidget(r);
    h4->addWidget(s);
    h4->addWidget(t);
    v1 = new QVBoxLayout();
    v1->addWidget(title);
    v1->addWidget(screen1);
    v1->addLayout(h1);
    v1->addLayout(h2);
    v1->addLayout(h3);
    v1->addLayout(h4);

    setLayout(v1);
    QWidget::connect(a,SIGNAL(clicked()),this,SLOT(func1()));
    QWidget::connect(b,SIGNAL(clicked()),this,SLOT(func2()));
    QWidget::connect(c,SIGNAL(clicked()),this,SLOT(func3()));
    QWidget::connect(f,SIGNAL(clicked()),this,SLOT(func4()));
    QWidget::connect(g,SIGNAL(clicked()),this,SLOT(func5()));
    QWidget::connect(h,SIGNAL(clicked()),this,SLOT(func6()));
    QWidget::connect(k,SIGNAL(clicked()),this,SLOT(func7()));
    QWidget::connect(l,SIGNAL(clicked()),this,SLOT(func8()));
    QWidget::connect(m,SIGNAL(clicked()),this,SLOT(func9()));
    QWidget::connect(q,SIGNAL(clicked()),this,SLOT(func0()));
    QWidget::connect(p,SIGNAL(clicked()),this,SLOT(funcdeci()));
    QWidget::connect(i,SIGNAL(clicked()),this,SLOT(funcadd()));
    QWidget::connect(n,SIGNAL(clicked()),this,SLOT(funcmul()));
    QWidget::connect(j,SIGNAL(clicked()),this,SLOT(funcsub()));
    QWidget::connect(s,SIGNAL(clicked()),this,SLOT(funcmod()));
    QWidget::connect(o,SIGNAL(clicked()),this,SLOT(funcdiv()));
    QWidget::connect(d,SIGNAL(clicked()),this,SLOT(funcon()));
    QWidget::connect(e,SIGNAL(clicked()),this,SLOT(funcoff()));
    QWidget::connect(t,SIGNAL(clicked()),this,SLOT(funcclr()));
    QWidget::connect(r,SIGNAL(clicked()),this,SLOT(funceql()));
   }

Widget::~Widget()
{
    
}
void Widget::func1(){
    qDebug()<< " Hello";

    screen1->setText(screen1->text()+"1");


}
void Widget::func2(){
    screen1->setText(screen1->text()+"2");
}
void Widget::func3(){
    screen1->setText(screen1->text()+"3");
}
            void Widget::func4(){
                screen1->setText(screen1->text()+"4");
}
void Widget::func5(){
    screen1->setText(screen1->text()+"5");
}
void Widget::func6(){
    screen1->setText(screen1->text()+"6");
}
void Widget::func7(){
    screen1->setText(screen1->text()+"7");
}
void Widget::func8(){
    screen1->setText(screen1->text()+"8");
}
void Widget::func9(){
    screen1->setText(screen1->text()+"9");
}
void Widget::func0(){
    screen1->setText(screen1->text()+"0");
}
void Widget::funcdeci(){
    screen1->setText(screen1->text()+".");
}
void Widget::funcon(){
    screen1->setText("on");
}
void Widget::funcoff(){
    this->close();
}
void Widget::funcclr(){
    screen1->setText("");
}
void Widget::funcadd(){
    z=1;
    varA=screen1->text().toFloat();
    screen1->setText("");
}
void Widget::funcsub(){
    z=2;
    varA=screen1->text().toFloat();
    screen1->setText("");
}
void Widget::funcmul(){
    z=3;
    varA=screen1->text().toFloat();
    screen1->setText("");
}
void Widget::funcdiv(){
    z=4;
    varA=screen1->text().toFloat();
    screen1->setText("");
}
void Widget::funcmod(){
    z=5;
    varA=screen1->text().toFloat();
    screen1->setText("");
}
void Widget::funceql(){
    varB=screen1->text().toFloat();
    switch(z)
    {
    case 1 : result = varA + varB ;break;
    case 2 : result = varA - varB ;break;
    case 3 : result = varA * varB ;break;
    case 4 : result = varA / varB ;break;
    case 5 :
        varC = varA;
        varD = varB;
        result = varC % varD ;break;
    }
    QString sss=QString::number(result);
    screen1->setText(sss);
}


