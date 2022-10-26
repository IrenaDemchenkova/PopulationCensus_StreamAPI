import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long juveniles = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + juveniles);

        List<String> recruiters = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() < 27)
                .filter(person -> person.getAge() >= 18)
                .map(Person::getFamily)
                .toList();
        System.out.println("Cписок призывников: ");
        System.out.println(recruiters);

Collection<Person> workable = persons.stream()
        .filter(person -> person.getAge() >=18)
        .filter(person -> person.getEducation().equals(Education.HIGHER))
        .filter(person -> (person.getSex().equals(Sex.MAN) && person.getAge()<65)||(person.getSex().equals(Sex.WOMAN) && person.getAge()<60))
        .sorted((o1, o2) -> o1.getFamily().compareToIgnoreCase(o2.getFamily()))
        .toList();
        System.out.println("Список потенциально работоспособных людей:");
        for (Person person:
             workable) {
            System.out.println(person);
        }
    }
}
