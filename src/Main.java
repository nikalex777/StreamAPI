import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("George", "Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long numberOfMinors = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + numberOfMinors);

        List<String> conscripts = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27 && x.getSex() == Sex.MAN)
                .map(Person::getFamily).toList();
        System.out.println("Фамилии призывников: " + conscripts);

        List<Person> workers = persons.stream()
                .filter(educ -> educ.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 61 && person.getSex() == Sex.WOMAN || person.getAge() >= 18 && person.getAge() <= 65 && person.getSex() == Sex.MAN)
                .sorted(Comparator.comparing(Person::getFamily)).toList();
        System.out.println("Потенциально работоспособные люди с высшим образованием: ");
        workers.forEach(System.out::println);
    }
}


