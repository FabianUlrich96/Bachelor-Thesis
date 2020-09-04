import pandas as pd
import seaborn as sns
from matplotlib import pyplot as plt


def select_answers():
    answer_df = pd.read_csv("kotlin_answer_complexity.csv", encoding='utf8')
    answer_df_ru = pd.read_csv("kotlin_answer_complexity_ru.csv", encoding='utf8')
    return answer_df, answer_df_ru


def select_questions():
    question_df = pd.read_csv("kotlin_question_complexity.csv", encoding='utf8')
    question_df_ru = pd.read_csv("kotlin_question_complexity_ru.csv", encoding='utf8')

    return question_df, question_df_ru


def plot_histogram(data, file_name, bins, x_label):
    sns.distplot(data, bins=bins)
    plt.xlabel(x_label, fontsize=15)
    plt.ylabel("Frequency", fontsize=15)
    plt.savefig(file_name)
    plt.show()


def plot_boxplot(data, file_name):
    ax = sns.boxplot(x="Platform", y="complexity", hue="Type", data=data)
    plt.xlabel("Platform", fontsize=15)
    plt.ylabel("Cyclomatic Complexity", fontsize=15)
    plt.savefig(file_name)
    plt.show()


def write_file(name, value):
    f = open(name, "a")
    f.write(value + "\n")
    f.close()


def main():
    answer_df, answer_df_ru = select_answers()
    question_df, question_df_ru = select_questions()

    answer_df = answer_df.assign(Type="Answer")
    question_df = question_df.assign(Type="Question")

    df_com = pd.concat([answer_df, question_df], join="outer", sort=False)

    df_com = df_com.assign(Platform="SO.com")

    answer_df_ru = answer_df_ru.assign(Type="Answer")
    question_df_ru = question_df_ru.assign(Type="Question")
    df_ru = pd.concat([answer_df_ru, question_df_ru], join="outer", sort=False)
    df_ru = df_ru.assign(Platform="SO.ru")

    df_com_ru = pd.concat([df_com, df_ru])
    print(df_com_ru)
    plot_boxplot(df_com_ru, "kotlin_com_ru_cc_boxplot.png")

    plot_histogram(answer_df['complexity'], "kotlin_SO.com_answers_cc_histogram.png", 100, "Cyclomatic Complexity")
    plot_histogram(question_df['complexity'], "kotlin_SO.com_questions_cc_histogram.png", 100, "Cyclomatic Complexity")
    plot_histogram(answer_df_ru['complexity'], "kotlin_SO.ru_answers_cc_histogram.png", 100, "Cyclomatic Complexity")
    plot_histogram(question_df_ru['complexity'], "kotlin_SO.ru_questions_cc_histogram.png", 100, "Cyclomatic Complexity")

    plot_histogram(answer_df['line_complexity'], "kotlin_SO.com_answer_cc_line_histogram.png", 100,
                   "Cyclomatic Complexity Density")
    plot_histogram(question_df['line_complexity'], "kotlin_SO.com_question_cc_line_histogram.png", 100,
                   "Cyclomatic Complexity Density")

    plot_histogram(answer_df_ru['line_complexity'], "kotlin_SO.ru_answer_cc_line_histogram.png", 100,
                   "Cyclomatic Complexity Density")
    plot_histogram(question_df_ru['line_complexity'], "kotlin_SO.ru_question_cc_line_histogram.png", 100,
                   "Cyclomatic Complexity Density")

    df_ru = pd.concat([answer_df_ru, question_df_ru])
    df_ru = df_ru.assign(Location="SO.ru")


    answer_median = answer_df.median(axis=0)
    answer_mode = answer_df.mode(axis=0)
    answer_mean = answer_df.mean(axis=0)
    answer_std = answer_df.std(axis=0)
    write_file("Kotlin_Results", "Answer Median =" + str(answer_median.round(2)))
    write_file("Kotlin_Results", "Answer Mode =" + str(answer_mode.round(2)))
    write_file("Kotlin_Results", "Answer Mean =" + str(answer_mean.round(2)))
    write_file("Kotlin_Results", "Answer std =" + str(answer_std.round(2)))


    question_median = question_df.median(axis=0)
    question_mode = question_df.mode(axis=0)
    question_mean = question_df.mean(axis=0)
    question_std = question_df.std(axis=0)

    write_file("Kotlin_Results", "Question Median =" + str(question_median.round(2)))
    write_file("Kotlin_Results", "Question Mode =" + str(question_mode.round(2)))
    write_file("Kotlin_Results", "Question Mean =" + str(question_mean.round(2)))
    write_file("Kotlin_Results", "Question std =" + str(question_std.round(2)))



    answer_median_ru = answer_df_ru.median(axis=0)
    answer_mode_ru = answer_df_ru.mode(axis=0)
    answer_mean_ru = answer_df_ru.mean(axis=0)
    answer_std_ru = answer_df_ru.std(axis=0)

    write_file("Kotlin_Results_Ru", "Answer Median =" + str(answer_median_ru.round(2)))
    write_file("Kotlin_Results_Ru", "Answer Mode =" + str(answer_mode_ru.round(2)))
    write_file("Kotlin_Results_Ru", "Answer Mean =" + str(answer_mean_ru.round(2)))
    write_file("Kotlin_Results_Ru", "Answer std =" + str(answer_std_ru.round(2)))

    question_median_ru = question_df_ru.median(axis=0)
    question_mode_ru = question_df_ru.mode(axis=0)
    question_mean_ru = question_df_ru.mean(axis=0)
    question_std_ru = question_df_ru.std(axis=0)

    write_file("Kotlin_Results_Ru", "Question Median =" + str(question_median_ru.round(2)))
    write_file("Kotlin_Results_Ru", "Question Mode =" + str(question_mode_ru.round(2)))
    write_file("Kotlin_Results_Ru", "Question Mean =" + str(question_mean_ru.round(2)))
    write_file("Kotlin_Results_Ru", "Question std =" + str(question_std_ru.round(2)))


if __name__ == "__main__":
    main()