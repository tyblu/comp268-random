:: Name:		putTextIOHardLinkThere.cmd
:: Purpose:		Puts a hard link of TextIO.java into a folder.
::				Used exclusively in my COMP268 'comp268-random'
::              git repo, with TextIO.java at its base.
:: Author:		Tyler Lucas
:: Revision:	2017-05-11	Initial version.

::::::::::::::::::::::::::::::::::::::::::::
:: Elevate.cmd - Version 4
:: Automatically check & get admin rights
:: https://stackoverflow.com/questions/7044985/how-can-i-auto-elevate-my-batch-file-so-that-it-requests-from-uac-administrator
::::::::::::::::::::::::::::::::::::::::::::
 @echo off
 CLS
 ECHO.
 ECHO =============================
 ECHO Running Admin shell
 ECHO =============================

:init
 setlocal DisableDelayedExpansion
 set cmdInvoke=1
 set winSysFolder=System32
 set "batchPath=%~0"
 for %%k in (%0) do set batchName=%%~nk
 set "vbsGetPrivileges=%temp%\OEgetPriv_%batchName%.vbs"
 setlocal EnableDelayedExpansion

:checkPrivileges
  NET FILE 1>NUL 2>NUL
  if '%errorlevel%' == '0' ( goto gotPrivileges ) else ( goto getPrivileges )

:getPrivileges
  if '%1'=='ELEV' (echo ELEV & shift /1 & goto gotPrivileges)
  ECHO.
  ECHO **************************************
  ECHO Invoking UAC for Privilege Escalation
  ECHO **************************************

  ECHO Set UAC = CreateObject^("Shell.Application"^) > "%vbsGetPrivileges%"
  ECHO args = "ELEV " >> "%vbsGetPrivileges%"
  ECHO For Each strArg in WScript.Arguments >> "%vbsGetPrivileges%"
  ECHO args = args ^& strArg ^& " "  >> "%vbsGetPrivileges%"
  ECHO Next >> "%vbsGetPrivileges%"

  if '%cmdInvoke%'=='1' goto InvokeCmd 

  ECHO UAC.ShellExecute "!batchPath!", args, "", "runas", 1 >> "%vbsGetPrivileges%"
  goto ExecElevation

:InvokeCmd
  ECHO args = "/c """ + "!batchPath!" + """ " + args >> "%vbsGetPrivileges%"
  ECHO UAC.ShellExecute "%SystemRoot%\%winSysFolder%\cmd.exe", args, "", "runas", 1 >> "%vbsGetPrivileges%"

:ExecElevation
 "%SystemRoot%\%winSysFolder%\WScript.exe" "%vbsGetPrivileges%" %*
 exit /B

:gotPrivileges
 setlocal & cd /d %~dp0
 if '%1'=='ELEV' (del "%vbsGetPrivileges%" 1>nul 2>nul  &  shift /1)

 ::::::::::::::::::::::::::::
 ::START
 ::::::::::::::::::::::::::::
 REM Run shell as admin (example) - put here code as you like

@echo off
SETLOCAL ENABLEEXTENSIONS
SET me=%~n0
SET parent=%~dp0

SET TextIOlog=%parent%\%me%.txt

IF NOT EXIST "%TextIOlog%" TYPE NUL > "%TextIOlog%" && CALL :logthis "Log file created for %parent%%me%.cmd"

SET /P "projectpath=Enter child file path: %parent%"
SET TextIOhome=%parent%TextIO.java
SET TextIOlink=%parent%%projectpath%\src\TextIO.java

CALL :logthis "Creating hard link of TextIO.java at %projectpath%\src\TextIO.java"

mklink /H "%TextIOlink%" "%TextIOhome%" && CALL :logthis "Hard link created successfully"

SED '$d'

:: logging function
:logthis
SET logline=%*
SET logline=%logline:"=%
IF NOT "x%logline:ELEV=%" == "x%logline%" (EXIT /B 0)
ECHO %me% [%DATE% %TIME:~0,2%:%TIME:~3,2%:%TIME:~6,2%]: %logline% >> "%TextIOlog%"
ECHO %me%: %logline%
EXIT /B 0


:: ECHO %batchName% Arguments: P1=%1 P2=%2 P3=%3 P4=%4 P5=%5 P6=%6 P7=%7 P8=%8 P9=%9
:: cmd /k