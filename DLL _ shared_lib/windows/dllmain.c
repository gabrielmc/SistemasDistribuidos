/* Replace "dll.h" with the name of your header */
#include "dll.h"
#include <windows.h>
#include <stdio.h>
#include <stdlib.h>

DLLIMPORT void HelloWorld (){
    MessageBox (0, "Hello World from DLL!\n", "Hi", MB_ICONINFORMATION);
}

DLLIMPORT int soma(int a,int b){
    return a+b;
}

//HINSTANCE hInst     /* Library instance handle. */ ,
//DWORD reason        /* Reason this function is being called. */
BOOL APIENTRY DllMain (HINSTANCE hInst, DWORD reason){
    switch (reason)
    {
      case DLL_PROCESS_ATTACH:
        break;
      case DLL_PROCESS_DETACH:
        break;
      case DLL_THREAD_ATTACH:
        break;
      case DLL_THREAD_DETACH:
        break;
    }
    return TRUE;
}
