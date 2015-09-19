#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <stdio.h>
#include <string>

using namespace std;

string addString(string s1, string s2)
{
    if(s1.size() != s2.size())
    return 0;
    
    string res(s1.size(), '0');
    for(int i = 0; i < s1.size(); i++){
        res[i] = ((s1[i] -'0') + (s2[i] - '0'))%10 + '0';
    }
    return res;
}

int main () {
    
    vector<string> list;
    ifstream in_stream;
    string line;
    string filename = string("input.txt");
    in_stream.open(filename);
    
    string tmp = string("");
    
    while(!in_stream.eof()){
        in_stream >> line;
        
        if(line.compare("z") == 0){ // if z, break
            break;
        }
        if(line.compare("x") == 0){ // if x, store tmp in list
            list.push_back(tmp);
            tmp.assign("");
            continue;
        }
        else{// if number, add to tmp
            if(tmp.compare("") == 0){
                tmp.assign(line);
            }
            else{
                tmp.assign(addString(tmp,line));
            }
        }
        
    }
    
    in_stream.close();
    
    // write to output file
    ofstream myfile ("output.txt");
    if (myfile.is_open())
    {
        for(int i = 0; i < list.size(); i++){
            myfile <<list[i]<<endl;
        }
        myfile.close();
    }
    else cout << "Unable to open file";
}

