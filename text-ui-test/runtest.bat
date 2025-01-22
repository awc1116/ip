@ECHO OFF

REM Create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM Delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist diff.txt del diff.txt

REM Compile the code into the bin folder
echo Compiling Java files...
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM Run the program, feed commands from input.txt file, and redirect the output to ACTUAL.TXT
echo Running program...
java -classpath ..\bin Doopies < input.txt > ACTUAL.TXT
IF ERRORLEVEL 1 (
    echo ********** PROGRAM EXECUTION FAILURE **********
    exit /b 1
)

REM Normalize files by trimming whitespace and converting to consistent line endings
echo Normalizing files...
for /f "tokens=* delims=" %%a in (ACTUAL.TXT) do echo %%a >> ACTUAL_NORMALIZED.TXT
for /f "tokens=* delims=" %%a in (EXPECTED.TXT) do echo %%a >> EXPECTED_NORMALIZED.TXT

REM Compare the normalized output
echo Comparing output...
FC ACTUAL_NORMALIZED.TXT EXPECTED_NORMALIZED.TXT > diff.txt
IF ERRORLEVEL 1 (
    echo ********** TEST FAILURE **********
    echo Differences found between ACTUAL.TXT and EXPECTED.TXT:
    type diff.txt
    del diff.txt
    del ACTUAL_NORMALIZED.TXT
    del EXPECTED_NORMALIZED.TXT
    exit /b 1
)

REM Clean up and success message
del diff.txt
del ACTUAL_NORMALIZED.TXT
del EXPECTED_NORMALIZED.TXT
echo ALL tests passed successfully!
