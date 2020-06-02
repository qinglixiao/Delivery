abstract class NetBean {}

class ServerData<T extends NetBean> {
  T data;
  int code;
  Error error;
}
