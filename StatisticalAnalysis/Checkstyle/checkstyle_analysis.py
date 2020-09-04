import pandas as pd
import seaborn as sns
from matplotlib import pyplot as plt


def select_answers():
    answer_df_com = pd.read_csv("SO.com_Checkstyle_Answers_categorized.csv", encoding='utf8')
    answer_df_ru = pd.read_csv("SO.ru_Checkstyle_Answers_categorized.csv", encoding='utf8')
    return answer_df_com, answer_df_ru


def select_questions():
    question_df_com = pd.read_csv("SO.com_Checkstyle_Questions_categorized.csv", encoding='utf8')
    question_df_ru = pd.read_csv("SO.ru_Checkstyle_Questions_categorized.csv", encoding='utf8')
    return question_df_com, question_df_ru


def plot_boxplot(data, file_name):
    ax = sns.boxplot(x="Category", y="error_count", hue="Type", data=data, showfliers=False)
    plt.rcParams['font.size'] = 7
    plt.xlabel("Category", fontsize=15)
    plt.ylabel("Errors", fontsize=15)
    plt.savefig(file_name)
    plt.show()


def write_file(name, value):
    f = open(name, "a")
    f.write(value + "\n")
    f.close()


def get_statistics(data, name):
    median = data.median(axis=0)
    mode = data.mode(axis=0)
    mean = data.mean(axis=0)
    std = data.std(axis=0)

    print(str(name) + "\nMedian:" + str(median) + "\nMode:" + str(mode) + "\nMean" + str(mean) + "\nStd:" + str(std) + "\n\n" )


def main():
    answer_df_com, answer_df_ru = select_answers()
    question_df_com, question_df_ru = select_questions()

    # SO.com dataframe
    form_answer_df_com = answer_df_com[['incorrect_form']].copy()
    form_answer_df_com = form_answer_df_com.assign(Category="incorrect_form")
    form_answer_df_com = form_answer_df_com.rename(columns={'incorrect_form': 'error_count'})

    placement_answer_df_com = answer_df_com[['incorrect_placement']].copy()
    placement_answer_df_com = placement_answer_df_com.assign(Category="incorrect_placement")
    placement_answer_df_com = placement_answer_df_com.rename(columns={'incorrect_placement': 'error_count'})

    whitespace_answer_df_com = answer_df_com[['incorrect_whitespace']].copy()
    whitespace_answer_df_com = whitespace_answer_df_com.assign(Category="incorrect_whitespace")
    whitespace_answer_df_com = whitespace_answer_df_com.rename(columns={'incorrect_whitespace': 'error_count'})

    use_answer_df_com = answer_df_com[['incorrect_use']].copy()
    use_answer_df_com = use_answer_df_com.assign(Category="incorrect_use")
    use_answer_df_com = use_answer_df_com.rename(columns={'incorrect_use': 'error_count'})


    form_question_df_com = question_df_com[['incorrect_form']].copy()
    form_question_df_com = form_question_df_com.assign(Category="incorrect_form")
    form_question_df_com = form_question_df_com.rename(columns={'incorrect_form': 'error_count'})

    placement_question_df_com = question_df_com[['incorrect_placement']].copy()
    placement_question_df_com = placement_question_df_com.assign(Category="incorrect_placement")
    placement_question_df_com = placement_question_df_com.rename(columns={'incorrect_placement': 'error_count'})

    whitespace_question_df_com = question_df_com[['incorrect_whitespace']].copy()
    whitespace_question_df_com = whitespace_question_df_com.assign(Category="incorrect_whitespace")
    whitespace_question_df_com = whitespace_question_df_com.rename(columns={'incorrect_whitespace': 'error_count'})

    use_question_df_com = question_df_com[['incorrect_use']].copy()
    use_question_df_com = use_question_df_com.assign(Category="incorrect_use")
    use_question_df_com = use_question_df_com.rename(columns={'incorrect_use': 'error_count'})

    df_answers_com = pd.concat([form_answer_df_com, placement_answer_df_com, whitespace_answer_df_com, use_answer_df_com])
    df_answers_com = df_answers_com.assign(Type="Answers")

    df_questions_com = pd.concat(
        [form_question_df_com, placement_question_df_com, whitespace_question_df_com, use_question_df_com])
    df_questions_com = df_questions_com.assign(Type="Questions")

    df_com = pd.concat(
        [df_answers_com, df_questions_com])

    # SO.ru dataframe
    form_answer_df_ru = answer_df_ru[['incorrect_form']].copy()
    form_answer_df_ru = form_answer_df_ru.assign(Category="incorrect_form")
    form_answer_df_ru = form_answer_df_ru.rename(columns={'incorrect_form': 'error_count'})

    placement_answer_df_ru = answer_df_ru[['incorrect_placement']].copy()
    placement_answer_df_ru = placement_answer_df_ru.assign(Category="incorrect_placement")
    placement_answer_df_ru = placement_answer_df_ru.rename(columns={'incorrect_placement': 'error_count'})


    whitespace_answer_df_ru = answer_df_ru[['incorrect_whitespace']].copy()
    whitespace_answer_df_ru = whitespace_answer_df_ru.assign(Category="incorrect_whitespace")
    whitespace_answer_df_ru = whitespace_answer_df_ru.rename(columns={'incorrect_whitespace': 'error_count'})

    use_answer_df_ru = answer_df_ru[['incorrect_use']].copy()
    use_answer_df_ru = use_answer_df_ru.assign(Category="incorrect_use")
    use_answer_df_ru = use_answer_df_ru.rename(columns={'incorrect_use': 'error_count'})

    form_question_df_ru = question_df_ru[['incorrect_form']].copy()
    form_question_df_ru = form_question_df_ru.assign(Category="incorrect_form")
    form_question_df_ru = form_question_df_ru.rename(columns={'incorrect_form': 'error_count'})

    placement_question_df_ru = question_df_ru[['incorrect_placement']].copy()
    placement_question_df_ru = placement_question_df_ru.assign(Category="incorrect_placement")
    placement_question_df_ru = placement_question_df_ru.rename(columns={'incorrect_placement': 'error_count'})

    whitespace_question_df_ru = question_df_ru[['incorrect_whitespace']].copy()
    whitespace_question_df_ru = whitespace_question_df_ru.assign(Category="incorrect_whitespace")
    whitespace_question_df_ru = whitespace_question_df_ru.rename(columns={'incorrect_whitespace': 'error_count'})

    use_question_df_ru = question_df_ru[['incorrect_use']].copy()
    use_question_df_ru = use_question_df_ru.assign(Category="incorrect_use")
    use_question_df_ru = use_question_df_ru.rename(columns={'incorrect_use': 'error_count'})

    df_answers_ru = pd.concat(
        [form_answer_df_ru, placement_answer_df_ru, whitespace_answer_df_ru, use_answer_df_ru])
    df_answers_ru = df_answers_ru.assign(Type="Answers")
    df_questions_ru = pd.concat(
        [form_question_df_ru, placement_question_df_ru, whitespace_question_df_ru, use_question_df_ru])
    df_questions_ru = df_questions_ru.assign(Type="Questions")

    df_ru = pd.concat(
        [df_answers_ru, df_questions_ru])
    df_ru = df_ru[df_ru.error_count != 0]
    df_com = df_com[df_com.error_count != 0]

    form_answer_df_ru = form_answer_df_ru[form_answer_df_ru.error_count != 0]
    form_answer_df_com = form_answer_df_com[form_answer_df_com.error_count != 0]
    form_question_df_ru = form_question_df_ru[form_question_df_ru.error_count != 0]
    form_question_df_com = form_question_df_com[form_question_df_com.error_count != 0]

    placement_answer_df_ru = placement_answer_df_ru[placement_answer_df_ru.error_count != 0]
    placement_answer_df_com = placement_answer_df_com[placement_answer_df_com.error_count != 0]
    placement_question_df_ru = placement_question_df_ru[placement_question_df_ru.error_count != 0]
    placement_question_df_com = placement_question_df_com[placement_question_df_com.error_count != 0]

    whitespace_answer_df_ru = whitespace_answer_df_ru[whitespace_answer_df_ru.error_count != 0]
    whitespace_answer_df_com = whitespace_answer_df_com[whitespace_answer_df_com.error_count != 0]
    whitespace_question_df_ru = whitespace_question_df_ru[whitespace_question_df_ru.error_count != 0]
    whitespace_question_df_com = whitespace_question_df_com[whitespace_question_df_com.error_count != 0]

    use_question_df_ru = use_question_df_ru[use_question_df_ru.error_count != 0]
    use_question_df_com = use_question_df_com[use_question_df_com.error_count != 0]
    use_answer_df_ru = use_answer_df_ru[use_answer_df_ru.error_count != 0]
    use_answer_df_com = use_question_df_com[use_question_df_com.error_count != 0]

    get_statistics(form_answer_df_ru, "form_answers_ru")
    get_statistics(form_question_df_ru, "form_questions_ru")
    get_statistics(form_answer_df_com, "form_answers_com")
    get_statistics(form_question_df_com, "form_questions_com")

    get_statistics(placement_answer_df_ru, "placement_answers_ru")
    get_statistics(placement_question_df_ru, "placement_questions_ru")
    get_statistics(placement_answer_df_com, "placement_answers_com")
    get_statistics(placement_question_df_com, "placement_questions_com")

    get_statistics(whitespace_answer_df_ru, "whitespace_answers_ru")
    get_statistics(whitespace_question_df_ru, "whitespace_questions_ru")
    get_statistics(whitespace_answer_df_com, "whitespace_answers_com")
    get_statistics(whitespace_question_df_com, "whitespace_questions_com")

    get_statistics(use_answer_df_ru, "use_answers_ru")
    get_statistics(use_question_df_ru, "use_questions_ru")
    get_statistics(use_answer_df_com, "use_answers_com")
    get_statistics(use_question_df_com, "use_questions_com")

    # first plot font size issue
    plot_boxplot(df_com, "trash.png")

    plot_boxplot(df_com, "com_checkstyle_boxplot.png")
    plot_boxplot(df_ru, "ru_checkstyle_boxplot.png")


if __name__ == "__main__":
    main()