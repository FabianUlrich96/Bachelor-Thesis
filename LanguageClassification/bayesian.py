import pandas as pd


def process_df(df, classifier):
    code_df = pd.DataFrame(columns=['id', 'code_snippet', 'language'])
    for row in df.itertuples():
        post_id = row[1]
        snippet = row[2]
        result = classifier.classify(snippet)
        for value, key in result.items():
            new_row = {'id': post_id, 'code_snippet': snippet, 'probability': value}
            code_df = code_df.append(new_row, ignore_index=True)
    return code_df


def create_dataframe(java_df, kotlin_df):
    temp_df = pd.DataFrame(columns=['id', 'code_snippet', 'language', 'probability'])
    temp_df['code_snippet'] = java_df['code_snippet']
    temp_df['id'] = java_df['id'].values
    temp_df['java'] = java_df['probability'].values
    temp_df['kotlin'] = kotlin_df['probability'].values

    language_df = pd.DataFrame(columns=['id', 'code_snippet', 'language'])

    language_df['language'] = temp_df[["java", "kotlin"]].idxmax(axis=1)
    language_df['id'] = temp_df['id']
    language_df['code_snippet'] = temp_df['code_snippet']
    language_df['probability'] = temp_df[["java", "kotlin"]].max(axis=1)

    return language_df


def train_java(classifier, df):
    classifier.train(open("../java_training.java", encoding="utf-8").read(), "java")
    code_df = pd.DataFrame(columns=['id', 'code_snippet', 'probability'])
    for chunk in df:
        df = process_df(chunk, classifier)
        code_df = code_df.append(df)
    return code_df


def train_kotlin(classifier, df):
    classifier.train(open("../kotlin_training.kt", encoding="utf-8").read(), "kotlin")
    code_df = pd.DataFrame(columns=['id', 'code_snippet', 'probability'])
    for chunk in df:
        df = process_df(chunk, classifier)
        code_df = code_df.append(df)
    print(code_df)
    return code_df

