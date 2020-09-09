namespace java thrift.generated

//typedef 起别名 示例 将i16 定义成 Java中的short
typedef i16 shrot
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String


struct Person{
    1:optional String username;
    2:optional int age;
    3:optional boolean marride;
}

exception DataException{
    1:optional String message;
    2:optional String callStack;
    3:optional String date;//thrift 不支持时间类型
}

service PersonService{
    Person getPersonByUsername(1:required String usernaem) throws (1:DataException dataException),
    void savePerson(1:required Person person) throws (1:DataException dataException)
}