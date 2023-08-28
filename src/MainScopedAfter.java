import java.util.UUID;

public class MainScopedAfter {
  public static void main(String[] args) {
    Gateway gateway = new Gateway();
    gateway.doSomething(new UserId(UUID.randomUUID()));
  }

}

record UserId(UUID value) {}

// AFTER JAVA 21
class Security {
  final static ScopedValue<UserId> USER = ScopedValue.newInstance();
}

class Gateway {
  final BusinessService businessService = new BusinessService();
  void doSomething(UserId userId) {
    ScopedValue.where(Security.USER, userId)
      .run(businessService::makeOperation);
  }
}

class BusinessService {
  void makeOperation() {
    var userId = Security.USER.get();
    System.out.println("Operation made by user" + userId.value());
  }
}
