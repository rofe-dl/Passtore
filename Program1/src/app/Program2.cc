#include <fstream>
#include <string>
#include <iostream>
#include <regex>

int main(){
    std::ifstream oldfile("file.txt");
    std::ofstream newfile("newfile.txt");
    std::string curr;
    std::string copy = "";

    while(std::getline(oldfile, curr)){
        std::string sub = "";

        if(curr.length() > 42)
            sub = curr.substr(39, 3);

        if(std::regex_match(sub, std::regex("[0-9][0-9][0-9]") )){
            std::string lineToCopy = curr.substr(0, 36);

            if(lineToCopy[0] == ' '){
                curr = copy + curr.substr(36);
            }else{
                copy = lineToCopy;
            }
        }

        newfile << curr << "\n";
    }

    oldfile.close();
    newfile.close();

    return 0;

}