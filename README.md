# Comparing the Code Quality of Multi-Lingual StackOverflow Communities

All files in this readme are listed in the order they got used for the creation of the methodology part of the thesis. 

## Thresholds

### com_Queries / ru_Queries:
SQL queries used to extract data using the Google BigQuery and the Stack Exchange Data Explorer.

### split_tags.py use-case: 
Requirements:
- package pandas

Reads a csv file with all tags from the posts which include at least one of the initial tags and splits the tags into one tag per row. In addition a copy of that results with only unique tags will be saved.

### count_total.py use-case:
Requirements:
- package pandas

Counts the total amount of times a tag got used in the input csv file. 

### TST_TRT_Evaluation_com / TST_TRT_Evaluation_ru:
Excel calculation file to evaluate the used tags after setting a threshold.


## Database Connection

### DataBaseConnection.py use-case:
Requirements:
- package sqlite3
- package pandas

General file to handle selects and saves to the in the file specified database.

## Select Code

### answer_code.py use-case:
Requirements:
- package re
- package pandas

Select snippets from answers with the pattern <code> ... </code> from the previously downloaded BigQuery request/ Stack Exchange Data Explorer request (code snippets are saved with that XML-markup in the Stack Exchange Database structure).
Save the results to a database. 

### answer_code_lenghts.py use-case:
Remove all single line answer code snippets. 

### question_code.py use-case:
Requirements:
- package re
- package pandas

Select snippets from questions with the pattern <code> ... </code> from the previously downloaded BigQuery request/ Stack Exchange Data Explorer request (code snippets are saved with that XML-markup in the Stack Exchange Database structure).
Save the results to a database.

### question_code_lenghts.py use-case:
Remove all single line question code snippets. 

## Language Classification

### SQL_training

SQL queries used to extract code snippets for training the classifier. 

### LangClassifier.py use-case:

Class to classify the code snippet with a Bayesian Classifier using the java_training.java and kotlin_training.kt training files.

### bayesian.py use-case:
Requirements:
- package pandas

Comparing the classification probabilities from the results of the LangClassifier class and returning a cleaned dataset to the classify_answer.py and classifiy_question.py files to save in the database.

### classify_answer.py use-case:

Interface to request answers from the DataBaseConnection class, request a programming language classification from the LangClassifier class, order and compare the results in the bayesian.py file and save the results to the database.

### classifiy_question.py use-case:

Interface to request questions from the DataBaseConnection class, request a programming language classification from the LangClassifier class, order and compare the results in the bayesian.py file and save the results to the database.

## Update Code Snippet

### java_update_code.py use-case:
Requirements:
- package pandas
Each Java code snippet need a class as a minimum requirement to run. Therefore this python file wraps the code snippet with a class if non of the trigger words is included in the snippet.
Trigger words:
- import 
- package
- class

### kotlin_update_code.py use-case:
Requirements:
- package pandas

Each Kotlin code snippet needs a main function as a minimum requirement to run. Therefore this python file adds a "fun main{}" to the beginning of the snippet if no "fun main" can be found.

## Cyclomatic Complexity Code
### complexity_answer.py use-case:
Interface that requests the answer selects from the DataBaseConnection class, requests a cyclomatic complexity result from the CyclomaticComplexityClass and saves the results to the database. 
### complexity_question.py use-case:
Interface that requests the question selects from the DataBaseConnection class, requests a cyclomatic complexity result from the CyclomaticComplexityClass and saves the results to the database. 

### CyclomaticComplexityClass.py use-case:
Requirements:
- package pandas

The cyclomatic complexity of a code snippet is assigned to 1 if non of the trigger words is included in the snippet. Each included trigger word increases the complexity by 1.
Trigger words:
- if
- else
- while
- for
- else if
- case with each -> in a case increasing it by 1

## Code Conventions

### checkstyle.py use-case:
Requirements:
- package subprocess
- package pandas
- package datatime
- package multiprocess
- package time

Executes the checkstyle-8.35all.jar in a commandline, searches for observed errors and saves the results to the database. 

### kt_lint.py use-case:
Requirements:
- package subprocess
- package pandas
- ktlint cmd tool: https://ktlint.github.io/

Executes the ktlint cmd tool, searches for the observed errors and saves the results to the database. 

## User Intersection

### get_user_data.py use-case:
Requirements: 
- package pandas
- package sqlite3

Request user id's from the stackoverflow.ru database, request the user profiles from the GetUserData class and save the results in the database.
### GetUserData.py use-case:
Requirements:
- package BeautifulSoup
- package get
- package time

Webscrape the user profiles from the ru.stackoverflow page and search if the user has a stackoverflow.com profile as well.

## StatisticalAnalysis
### Checkstyle
#### checkstyle_analysis.py: 
Requirements: 
- package pandas
- package seaborn
- matplotlib 
Main file for the statistical analysis of the Checkstyle results

#### checkstyle_analysis_popularity.py 
Requirements:
- package pandas
- package seaborn
- matplotlib 

File for the statistical analysis of the results by popularity on the platform by assigning unpopular, neutral and popular to each post using the score of each post.
Popular = Score of 5 and greater
Neutral = Score between 0 and 4
Unpopular = Score less than 0

The rest of the files are the resulting graphs and the .csv files that have been taken from the database for analysis.

### ktlint
#### ktlint_analysis.py
Requirements:
- package pandas
- package seaborn
- matplotlib 

Main file for the statistical analysis of the ktlint results.
The rest of the files are the resulting graphs and the .csv files that have been taken from the database for analysis.

### Cyclomatic Complexity
#### Java
##### java_analysis.py
Requirements:
- package pandas
- package seaborn
- matplotlib 
- numpy

Main file for analysing the Java Cyclomatic Complexity

##### java_lines_analysis.py
Requirements:
- package pandas

File to analyse the Java Cyclomatic Complexity Density

#### Kotlin
##### kotlin_analysis.py
Requirements:
- package pandas
- package seaborn
- matplotlib 

Main file for analysing the Kotlin Cyclomatic Complexity

##### kotlin_lines_analysis.py
Requirements:
- package pandas

File to analyse the Kotlin Cyclomatic Complexity Density



