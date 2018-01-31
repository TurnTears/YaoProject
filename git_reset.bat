@echo off
rem set /p branchName=please input branch name:
for /f "delims=" %%i in ('git symbolic-ref --short -q HEAD') do (set branchName=%%i)
@git fetch --all
@git reset --hard origin/"%branchName%"
@pause