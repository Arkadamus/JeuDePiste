//
// Created by Quintana on 03/10/2019.
//

#ifndef TESTMAP_CUSER_H
#define TESTMAP_CUSER_H
#include <string>


class Cuser {
private:
    int m_id;
    string m_login;
    string m_password;
    //Cmission m_mission_en_cours;
    //Cmission* mp_mission_effectuees;

public:
    Cuser();
    Cuser(string,string);
    ~Cuser();
};



#endif //TESTMAP_CUSER_H
