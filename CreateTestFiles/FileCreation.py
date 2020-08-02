import os


class FileCreation:

    @staticmethod
    def create_file(file_name, extension):
        if os.path.isfile(file_name + extension):
            f = open(file_name + extension, "a", encoding="utf-8")
        else:
            f = open(file_name + extension, "w+", encoding="utf-8")
        return f

    @staticmethod
    def file_size(file_name):
        file_stats = os.stat(file_name)
        size_megabyte = file_stats.st_size / (1024 * 1024)
        print(size_megabyte)
        return size_megabyte
