import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOneResultBySearch () {
        Task task = new Task();
        SimpleTask simpleTask = new SimpleTask(108, "Сдать курсовую");

        String[] subtasks = { "Кукуруза", "Молоко", "Мука" };
        Epic epic = new Epic(67, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Командная работа для проекта",
                "Курс JavaQA",
                "В среду утром"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        todos.search("работа");

        Task[] expected = {meeting};
        Task[] actual = todos.search("работа");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTwoResultsBySearch () {
        Task task = new Task();
        SimpleTask simpleTask = new SimpleTask(78, "Купить коржи для торта");

        String[] subtasks = { "коржи", "молоко", "сливки" };
        Epic epic = new Epic(99, subtasks);

        Meeting meeting = new Meeting(
                100,
                "Разработка нового урока",
                "Курс JavaQA",
                "В четверг 16:00"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        todos.search("коржи");

        Task[] expected = {simpleTask, epic};
        Task[] actual = todos.search("коржи");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSeveralResultsBySearch () {
        Task task = new Task();
        SimpleTask simpleTask = new SimpleTask(78, "Купить коржи для торта");

        String[] subtasks = { "коржи для торта", "молоко", "сливки" };
        Epic epic = new Epic(99, subtasks);

        Meeting meeting = new Meeting(
                100,
                "Разработка нового рецепта торта",
                "Торт на день рождения",
                "В четверг 16:00"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        todos.search("торта");

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.search("торта");
        Assertions.assertArrayEquals(expected, actual);
    }


}
