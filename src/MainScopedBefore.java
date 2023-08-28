//import java.util.UUID;
//
//public class MainScopedBefore {
//  public static void main(String[] args) {
//    Gateway gateway = new Gateway();
//    gateway.doSomething(new UserId(UUID.randomUUID()));
//  }
//
//}
//
//record UserId(UUID value) {}
//
//// BEFORE JAVA 21
//class Security {
//  final static ThreadLocal<UserId> USER = new ThreadLocal<>();
//}
//
//class Gateway {
//  final BusinessService businessService = new BusinessService();
//
//  void doSomething(UserId userId) {
//    Security.USER.set(userId);
//    businessService.makeOperation();
//  }
//}
//
//class BusinessService {
//  void makeOperation() {
//    var userId = Security.USER.get();
//    System.out.println("Operation made by user" + userId.value());
//  }
//}
//
//// JAVA 21
