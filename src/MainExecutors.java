import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

public class MainExecutors {

    public static void main(String[] args) {
        Gateway gateway = new Gateway();
        gateway.doSomething(new UserId(UUID.randomUUID()));
    }

//  Invoice generate() throws ExecutionException, InterruptedException {
//    try (var executorService = Executors.newFixedThreadPool(3)) {
//      Future<Issuer> issuer = executorService.submit(this::findIssuer);
//      Future<Customer> customer = executorService.submit(this::findCustomer);
//      Future<List<Item>> items = executorService.submit(this::findItems);
//
//      return new Invoice(issuer.get(), customer.get(), items.get());
//    }
//  }

    User handle() throws ExecutionException, InterruptedException {
        try (var executor = Executors.newFixedThreadPool(3)) {
            Future<UUID> id = executor.submit(() -> fetchId());
            Future<String> name = executor.submit(() -> fetchName());
            return new User(id.get(), name.get());
        }
    }

    User generate() throws ExecutionException, InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Supplier<UUID> id = scope.fork(() -> fetchId());
            Supplier<String> name = scope.fork(() -> fetchName());
            scope.join().throwIfFailed();
            return new User(id.get(), name.get());
        }
    }
    public record User(UUID uuid, String s) {

    }

    UUID fetchId(){
        return UUID.randomUUID();
    }

    String fetchName(){
        return "";
    }
}
