import pandas as pd


class CyclomaticComplexity:

    @staticmethod
    def calculate_complexity(snippet):
        if1_string = "if ("
        if2_string = "if("
        else1_string = "else ("
        else2_string = "else("
        while1_string = "while ("
        while2_string = "while("
        for1_string = "for ("
        for2_string = "for("
        elseif1_string = "else if ("
        elseif2_string = "else if("
        case_string = "case"
        when_string = "->"

        if_count = snippet.count(if1_string) + snippet.count(if2_string)
        else_count = snippet.count(else1_string) + snippet.count(else2_string)
        while_count = snippet.count(while1_string) + snippet.count(while2_string)
        for_count = snippet.count(for1_string) + snippet.count(for2_string)
        elseif_count = snippet.count(elseif1_string) + snippet.count(elseif2_string)

        if elseif_count != 0:
            elseif_count = elseif_count - if_count

        if "switch (" or "switch(" in snippet:
            switch_count = 1 + snippet.count(case_string)
        else:
            switch_count = 0

        if "when (" or "when(" in snippet:
            when_count = snippet.count(when_string)
        else:
            when_count = 0

        cyclomatic_complexity = if_count + else_count + while_count + for_count + elseif_count + switch_count + when_count

        return cyclomatic_complexity

    @staticmethod
    def test_complexity(kotlin_df, java_df):
        kotlin_complexity = pd.DataFrame(columns=['id', 'code_snippet', 'complexity', 'n_lines', 'line_complexity'])
        java_complexity = pd.DataFrame(columns=['id', 'code_snippet', 'complexity', 'n_lines', 'line_complexity'])
        for kotlin_chunk in kotlin_df:
            for kotlin_row in kotlin_chunk.itertuples():
                snippet_id = kotlin_row[1]
                kotlin_snippet = kotlin_row[2]
                complexity = CyclomaticComplexity.calculate_complexity(kotlin_snippet)
                n_lines = kotlin_snippet.count('\n')
                line_complexity = complexity/n_lines
                new_row = {'id': snippet_id, 'code_snippet': kotlin_snippet, 'complexity': complexity, 'n_lines': n_lines, 'line_complexity': line_complexity}
                kotlin_complexity = kotlin_complexity.append(new_row, ignore_index=True)

        for java_chunk in java_df:
            for java_row in java_chunk.itertuples():
                snippet_id = java_row[1]
                java_snippet = java_row[2]
                complexity = CyclomaticComplexity.calculate_complexity(java_snippet)
                n_lines = java_snippet.count('\n')
                line_complexity = complexity / n_lines
                new_row = {'id': snippet_id, 'code_snippet': java_snippet, 'complexity': complexity, 'n_lines': n_lines, 'line_complexity': line_complexity}
                java_complexity = java_complexity.append(new_row, ignore_index=True)
        return kotlin_complexity, java_complexity
