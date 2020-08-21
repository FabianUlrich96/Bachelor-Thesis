import subprocess
from DataBaseConnection.DataBaseConnection import DataBaseConnection
import pandas as pd
from datetime import datetime
from multiprocessing import Process
import time


def get_connection():
    database_connection = DataBaseConnection()
    db = DataBaseConnection.connect_database(database_connection)
    return database_connection, db


def temp_file(snippet, name):
    f = open(name, "w", encoding="utf-8")
    f.write(snippet)
    f.close()


def checkstyle_cmd():
    cmd = "java -jar checkstyle-8.35-all.jar -c /google_checks.xml JavaClass.java"
    proc = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    out, err = proc.communicate()
    str_out = str(out)
    print(str_out)
    abbreviation = "[AbbreviationAsWordInName]"
    left_curly = "[LeftCurly]"
    right_curly = "[RightCurlyAlone]"
    right_curly2 = "[RightCurlySame]"
    whitespace = "[WhitespaceAround]"
    empty_line = "[EmptyLineSeparator]"
    empty_block = "[EmptyBlock]"
    separator_wrap = "[SeparatorWrapComma]"
    indentation = "[Indentation]"
    comment_indentation = "[CommentsIndentation]"
    annotation = "[AnnotationLocationMostCases]"
    top_level = "[OneTopLevelClass]"
    array_type = "[ArrayTypeStyle]"
    invalid_position = "[InvalidJavadocPosition]"
    member_name = "[MemberName]"
    file_tab_character = "[FileTabCharacter]"
    switch_default = "[MissingSwitchDefault]"
    import_error = "[CustomImportOrder]" # star and static imports
    parameter_name = "[CatchParameterName]"
    modifier_order = "[ModifierOrder]"
    multiple_variable = "[MultipleVariableDeclarations]"
    braces = "[NeedBraces]"
    paren_pad = "[ParenPad]"
    method_paren_pad = "[MethodParamPad]"
    no_line_wrap = "[NoLineWrap]"
    overload_method = "[OverloadMethodsDeclarationOrder]"
    javadoc_paragraph = "[JavadocParagraph]"
    javadoc_indentation = "[JavadocTagContinuationIndentation]"
    javadoc_method = "[MissingJavadocMethod]"
    method_name = "[MethodName]"
    variable_name = "[LocalVariableName]"
    javadoc_single = "[SingleLineJavadoc]"
    no_finalizer = "[NoFinalizer]"
    fall_through = "[FallThrough]"
    operator_wrap = "[OperatorWrap]"
    summary_javadoc = "[SummaryJavadoc]"
    type_name = "[TypeName]"
    upper_l = "[UpperEll]"

    exception = "Exception was thrown while processing"

    abbreviation_count = str_out.count(abbreviation)
    left_curly_count = str_out.count(left_curly)
    right_curly_count = str_out.count(right_curly)
    right_curly2_count = str_out.count(right_curly2)
    whitespace_count = str_out.count(whitespace)
    empty_line_count = str_out.count(empty_line)
    empty_block_count = str_out.count(empty_block)
    separator_wrap_count = str_out.count(separator_wrap)
    indentation_count = str_out.count(comment_indentation)
    comment_indentation_count = str_out.count(comment_indentation)
    annotation_count = str_out.count(annotation)
    top_level_count = str_out.count(top_level)
    array_type_count = str_out.count(array_type)
    invalid_position_count = str_out.count(invalid_position)
    member_name_count = str_out.count(member_name)
    file_tab_character_count = str_out.count(file_tab_character)
    switch_default_count = str_out.count(switch_default)
    import_error_count = str_out.count(import_error)
    parameter_name_count = str_out.count(parameter_name)
    modifier_order_count = str_out.count(modifier_order)
    multiple_variable_count = str_out.count(multiple_variable)
    braces_count = str_out.count(braces)
    paren_pad_count = str_out.count(paren_pad)
    method_paren_pad_count = str_out.count(method_paren_pad)
    no_line_wrap_count = str_out.count(no_line_wrap)
    overload_method_count = str_out.count(overload_method)
    javadoc_paragraph_count = str_out.count(javadoc_paragraph)
    javadoc_indentation_count = str_out.count(javadoc_indentation)
    javadoc_method_count = str_out.count(javadoc_method)
    method_name_count = str_out.count(method_name)
    variable_name_count = str_out.count(variable_name)
    javadoc_single_count = str_out.count(javadoc_single)
    no_finalizer_count = str_out.count(no_finalizer)
    fall_through_count = str_out.count(fall_through)
    operator_wrap_count = str_out.count(operator_wrap)
    summary_javadoc_count = str_out.count(summary_javadoc)
    type_name_count = str_out.count(type_name)
    upper_l_count = str_out.count(upper_l)

    exception_count = str_out.count(exception)

    return exception_count, upper_l_count, type_name_count, summary_javadoc_count, operator_wrap_count, fall_through_count, no_finalizer_count, javadoc_single_count, variable_name_count, method_name_count, modifier_order_count,multiple_variable_count, braces_count, paren_pad_count, method_paren_pad_count, no_line_wrap_count, overload_method_count, javadoc_paragraph_count, javadoc_indentation_count, javadoc_method_count, abbreviation_count, left_curly_count, right_curly_count, right_curly2_count, whitespace_count, empty_line_count, empty_block_count, separator_wrap_count, indentation_count, comment_indentation_count, annotation_count, top_level_count, array_type_count, invalid_position_count, member_name_count, file_tab_character_count, switch_default_count, import_error_count, parameter_name_count


def save_answer(answer_df):
    connection, db = get_connection()
    DataBaseConnection.save_checkstyle_answers(connection, db, answer_df)


def save_question(question_df):
    connection, db = get_connection()
    DataBaseConnection.save_checkstyle_questions(connection, db, question_df)


def get_snippet_question():
    java_questions = pd.DataFrame(columns=['id', 'code_snippet', 'valid_file', 'upperel', 'typename', 'summaryjavadoc', 'operatorwrap', 'fallthrough', 'nofinalizer', 'javadocsingle', 'variablename', 'methodname', 'modifierorder', 'multiplevariable', 'braces', 'parenpad', 'methodparenpad', 'nolinewrap', 'overloadmethod', 'javadocparagraph', 'javadocindentation', 'javadocmethod', 'abbreviation', 'leftcurly', 'rightcurly', 'whitespace', 'emptyline', 'emptyblock', 'separatorwrap', 'indentation', 'commentindentation', 'annotation', 'toplevel', 'arraytype', 'invalidposition', 'membername', 'filetabcharacter', 'switchdefault', 'importerror', 'parametername'])

    connection, db = get_connection()
    question_df = DataBaseConnection.select_java_question_update(connection, db)

    for java_chunk in question_df:
        for row in java_chunk.itertuples():
            snippet_id = row[1]
            snippet = row[2]
            temp_file(snippet, "Question_Class.java")
            exception_count, upper_l_count, type_name_count, summary_javadoc_count, operator_wrap_count, fall_through_count, no_finalizer_count, javadoc_single_count, variable_name_count, method_name_count, modifier_order_count, multiple_variable_count, braces_count, paren_pad_count, method_paren_pad_count, no_line_wrap_count, overload_method_count, javadoc_paragraph_count, javadoc_indentation_count, javadoc_method_count, abbreviation_count, left_curly_count, right_curly_count, right_curly2_count, whitespace_count, empty_line_count, empty_block_count, separator_wrap_count, indentation_count, comment_indentation_count, annotation_count, top_level_count, array_type_count, invalid_position_count, member_name_count, file_tab_character_count, switch_default_count, import_error_count, parameter_name_count = checkstyle_cmd()
            if exception_count != 0:
                java_file = False
            else:
                java_file = True
            new_row = {'id': snippet_id, 'code_snippet': snippet, 'valid_file': java_file, 'upperel': upper_l_count, 'typename':type_name_count, 'summaryjavadoc': summary_javadoc_count, 'operatorwrap': operator_wrap_count, 'fallthrough': fall_through_count, 'nofinalizer': no_finalizer_count, 'javadocsingle': javadoc_single_count, 'variablename': variable_name_count, 'methodname': method_name_count, 'modifierorder': modifier_order_count, 'multiplevariable': multiple_variable_count, 'braces': braces_count, 'parenpad': paren_pad_count, 'methodparenpad': method_paren_pad_count, 'nolinewrap': no_line_wrap_count, 'overloadmethod': overload_method_count, 'javadocparagraph': javadoc_paragraph_count, 'javadocindentation': javadoc_indentation_count, 'javadocmethod': javadoc_method_count, 'abbreviation': abbreviation_count, 'leftcurly': left_curly_count, 'rightcurly': right_curly_count + right_curly2_count, 'whitespace': whitespace_count, 'emptyline': empty_line_count, 'emptyblock': empty_block_count, 'separatorwrap': separator_wrap_count, 'indentation': indentation_count, 'commentindentation': comment_indentation_count, 'annotation': annotation_count, 'toplevel': top_level_count, 'arraytype': array_type_count, 'invalidposition': invalid_position_count, 'membernane': member_name_count, 'filetabcharacter': file_tab_character_count, 'switchdefault': switch_default_count, 'importerror': import_error_count, 'parametername': parameter_name_count}
            java_questions = java_questions.append(new_row, ignore_index=True)
    save_question(java_questions)


def get_snippet_answer():
    java_answers = pd.DataFrame(columns=['id', 'code_snippet', 'valid_file', 'upperel', 'typename', 'summaryjavadoc', 'operatorwrap', 'fallthrough', 'nofinalizer', 'javadocsingle', 'variablename', 'methodname', 'modifierorder', 'multiplevariable', 'braces', 'parenpad', 'methodparenpad', 'nolinewrap', 'overloadmethod', 'javadocparagraph', 'javadocindentation', 'javadocmethod', 'abbreviation', 'leftcurly', 'rightcurly', 'whitespace', 'emptyline', 'emptyblock', 'separatorwrap', 'indentation', 'commentindentation', 'annotation', 'toplevel', 'arraytype', 'invalidposition', 'membername', 'filetabcharacter', 'switchdefault', 'importerror', 'parametername'])
    connection, db = get_connection()
    answer_df = DataBaseConnection.select_java_answer_update(connection, db)
    for java_chunk in answer_df:
        for row in java_chunk.itertuples():
            snippet_id = row[1]
            snippet = row[2]
            temp_file(snippet, "Answer_Class.java")
            exception_count, upper_l_count, type_name_count, summary_javadoc_count, operator_wrap_count, fall_through_count, no_finalizer_count, javadoc_single_count, variable_name_count, method_name_count, modifier_order_count, multiple_variable_count, braces_count, paren_pad_count, method_paren_pad_count, no_line_wrap_count, overload_method_count, javadoc_paragraph_count, javadoc_indentation_count, javadoc_method_count, abbreviation_count, left_curly_count, right_curly_count, right_curly2_count, whitespace_count, empty_line_count, empty_block_count, separator_wrap_count, indentation_count, comment_indentation_count, annotation_count, top_level_count, array_type_count, invalid_position_count, member_name_count, file_tab_character_count, switch_default_count, import_error_count, parameter_name_count = checkstyle_cmd()
            if exception_count != 0:
                java_file = False
            else:
                java_file = True
            new_row = {'id': snippet_id, 'code_snippet': snippet, 'valid_file': java_file, 'upperel': upper_l_count,
                       'typename': type_name_count, 'summaryjavadoc': summary_javadoc_count,
                       'operatorwrap': operator_wrap_count, 'fallthrough': fall_through_count,
                       'nofinalizer': no_finalizer_count, 'javadocsingle': javadoc_single_count,
                       'variablename': variable_name_count, 'methodname': method_name_count,
                       'modifierorder': modifier_order_count, 'multiplevariable': multiple_variable_count,
                       'braces': braces_count, 'parenpad': paren_pad_count, 'methodparenpad': method_paren_pad_count,
                       'nolinewrap': no_line_wrap_count, 'overloadmethod': overload_method_count,
                       'javadocparagraph': javadoc_paragraph_count, 'javadocindentation': javadoc_indentation_count,
                       'javadocmethod': javadoc_method_count, 'abbreviation': abbreviation_count,
                       'leftcurly': left_curly_count, 'rightcurly': right_curly_count + right_curly2_count,
                       'whitespace': whitespace_count, 'emptyline': empty_line_count, 'emptyblock': empty_block_count,
                       'separatorwrap': separator_wrap_count, 'indentation': indentation_count,
                       'commentindentation': comment_indentation_count, 'annotation': annotation_count,
                       'toplevel': top_level_count, 'arraytype': array_type_count,
                       'invalidposition': invalid_position_count, 'membername': member_name_count,
                       'filetabcharacter': file_tab_character_count, 'switchdefault': switch_default_count,
                       'importerror': import_error_count, 'parametername': parameter_name_count}
            java_answers = java_answers.append(new_row, ignore_index=True)
    save_answer(java_answers)


def main():
    now = datetime.now()

    current_time = now.strftime("%H:%M:%S")
    print("Current Time =", current_time)

    Process(target=get_snippet_answer).start()
    time.sleep(120)
    Process(target=get_snippet_question).start()

    now = datetime.now()

    current_time = now.strftime("%H:%M:%S")
    print("Current Time =", current_time)


if __name__ == "__main__":
    main()
