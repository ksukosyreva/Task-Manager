import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void shouldTrueSimpleTaskIfQueryMatchesTitle () {
        SimpleTask simpleTask = new SimpleTask(3,"Сделать д/з");
        Todos todos = new Todos();
        todos.add(simpleTask);
        simpleTask.matches("д/з");

        boolean expected = true;
        boolean actual = simpleTask.matches("д/з");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFalseSimpleTaskIfQueryNotMatchesTitle () {
        SimpleTask simpleTask = new SimpleTask(3,"Сделать д/з");
        Todos todos = new Todos();
        todos.add(simpleTask);
        simpleTask.matches("задание");

        boolean expected = false;
        boolean actual = simpleTask.matches("задание");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldTrueEpicIfQueryMatchesSubtask () {
        String [] subtasks = {"послушать урок", "прочитать д/з", "выполнить задание"};
        Epic epic = new Epic(23,subtasks);
        Todos todos = new Todos();
        todos.add(epic);
        epic.matches("прочитать");

        boolean expected = true;
        boolean actual = epic.matches("прочитать");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFalseEpicIfQueryNotMatchesSubtask () {
        String [] subtasks = {"послушать урок", "прочитать д/з", "выполнить задание"};
        Epic epic = new Epic(23,subtasks);
        Todos todos = new Todos();
        todos.add(epic);
        epic.matches("домашнее");

        boolean expected = false;
        boolean actual = epic.matches("домашнее");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTrueMeetingIfQueryMatchesTopic () {
        Meeting meeting = new Meeting(45,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда");
        Todos todos = new Todos();
        todos.add(meeting);
        meeting.matches("Выкатка");

        boolean expected = true;
        boolean actual = meeting.matches("Выкатка");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTrueMeetingIfQueryMatchesProject () {
        Meeting meeting = new Meeting(45,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда");
        Todos todos = new Todos();
        todos.add(meeting);
        meeting.matches("НетоБанк");

        boolean expected = true;
        boolean actual = meeting.matches("НетоБанк");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFalseMeetingIfQueryNotMatchesProjectOrTopic () {
        Meeting meeting = new Meeting(45,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда");
        Todos todos = new Todos();
        todos.add(meeting);
        meeting.matches("вторник");

        boolean expected = false;
        boolean actual = meeting.matches("вторник");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test () {
        Task task = new Task(3);
        Todos todos = new Todos();
        todos.add(task);
        task.matches("query");

        boolean expected = false;
        boolean actual = task.matches("query");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetTitleInSimpleTask () {
        SimpleTask simpleTask = new SimpleTask(7, "Название");

        String expected = "Название";
        String actual = simpleTask.getTitle();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetSubtasksInEpic () {
        String [] subtasks = {"урок", "книга", "тетрадь"};
        Epic epic = new Epic(85, subtasks);

        String [] expected = {"урок", "книга", "тетрадь"};
        String [] actual = epic.getSubtasks();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGetTopicProjectStartInMeeting () {
        Meeting meeting = new Meeting(222,
                "Выкатка 8й версии приложения",
                "Приложение TaskManager",
                "Во вторник утром");
        Todos todos = new Todos();
        todos.add(meeting);

        String expectedTopic = "Выкатка 8й версии приложения";
        String actualTopic = meeting.getTopic();
        Assertions.assertEquals(expectedTopic,actualTopic);

        String expectedProject = "Приложение TaskManager";
        String actualProject = meeting.getProject();
        Assertions.assertEquals(expectedProject, actualProject);

        String expectedStart = "Во вторник утром";
        String actualStart = meeting.getStart();
        Assertions.assertEquals(expectedTopic,actualTopic);
    }

    @Test
    public void shouldGetTaskId () {
        Task task = new Task(9);

        int expected = 9;
        int actual = task.getId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void hashCodeShouldBeEqualWhenObjectsAreEqual() {
        Task task1 = new Task(55);
        Task task2 = new Task(55);
        Assertions.assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    void testEqualsUsingAssertEquals() {
        Task task1 = new Task(42);
        Task task2 = new Task(42);
        Assertions.assertEquals(task1, task2);
    }

    @Test
    void testEqualsUsingAssertNotEquals() {
        Task task1 = new Task(42);
        Task task2 = new Task(100);
        Assertions.assertNotEquals(task1, task2); // пройдёт, так как id разные
    }

    @Test
    void testEqualsWithNull() {
        Task task = new Task(5);
        Assertions.assertNotEquals(null, task);    // эквивалентно assertFalse(task.equals(null))
    }

    @Test
    void testEqualsWithDifferentClass() {
        Task task = new Task(5);
        String notATask = "task";
        Assertions.assertNotEquals(notATask, task); // объекты разных классов не равны
    }

    @Test
    void equalsShouldReturnFalseWhenComparedToNull() {
        Task task = new Task(100);
        Assertions.assertFalse(task.equals(null));
    }

    // 2. Сравнение с объектом другого класса
    @Test
    void equalsShouldReturnFalseWhenComparedToDifferentClass() {
        Task task = new Task(100);
        String notTask = "task";
        Assertions.assertFalse(task.equals(notTask));
    }

    // 3. Сравнение с самим собой (рефлексивность)
    @Test
    void equalsShouldReturnTrueWhenSameObject() {
        Task task = new Task(100);
        Assertions.assertTrue(task.equals(task));
    }

    // 4. Равенство по id
    @Test
    void equalsShouldReturnTrueWhenSameId() {
        Task task1 = new Task(42);
        Task task2 = new Task(42);
        Assertions.assertTrue(task1.equals(task2));
    }

    // 5. Неравенство по id
    @Test
    void equalsShouldReturnFalseWhenDifferentId() {
        Task task1 = new Task(42);
        Task task2 = new Task(99);
        Assertions.assertFalse(task1.equals(task2));
    }
}
