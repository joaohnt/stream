import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();
        System.out.printf("Enter salary: ");
        double salary = sc.nextDouble();

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<People> list = new ArrayList<>();
            String line = br.readLine();

            while(line != null) {
                String[] parts = line.split(",");
                list.add(new People(parts[0],parts[1],Double.parseDouble(parts[2])));
                line = br.readLine();
            }

            System.out.println("Email of people whose salary is more than " + salary+":");
            Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
            List<String> names = list.stream().filter(p -> p.getSalary() > salary).map(p -> p.getEmail()).sorted(comp).collect(Collectors.toUnmodifiableList());
            names.forEach(System.out::println);

            System.out.print("Sum of salary of people whose name starts with 'H': ");
            double sum = list.stream().filter(p -> p.getName().startsWith("H")).map(p -> p.getSalary()).reduce(0.0, (x,y) -> x+y);
            System.out.println(sum);
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}