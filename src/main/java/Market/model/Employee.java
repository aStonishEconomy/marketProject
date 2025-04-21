package Market.model;

public class Employee {

        private String name;
        private long id;
        private double salary;

        public Employee(String name, long id, double salary) {
            this.name = name;
            this.id = id;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public long getId() {
            return id;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return "Market.model.Employee{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", salary=" + salary +
                    '}';
        }
    }
