#define BUILDDLL
#include <stdio.h>
#include "dll.h"

MINHADLL int soma(int a,int b){
    return a+b;
}

MINHADLL int sub(int a,int b){
     return a-b;
}
