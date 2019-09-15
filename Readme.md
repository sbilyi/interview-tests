All solutions gathered together in same project. Each solution implements Solution<I,O> interface. 

All implementation has been loaded into solutions variable in main: 
```$java
    public static void main(String[] args) {
        Map<String, Solution> solutions = getSolutions();
        runAllTests(solutions);
    }
```

Each solution has separate test runner, which could be invoked as this
```$java
    private static void runAllTests(Map<String, Solution> solutions) {
        testEesyOneSolution(solutions.get(EASY_ONE_PROC_EFF));
        System.out.println();
        testEesyOneSolution(solutions.get(EASY_ONE_MEMORY_EFF));
        System.out.println();
        testEasyTwoSolution(solutions.get(EASY_SECOND_SOLUTION));
        System.out.println();
        testModerateOne(solutions.get(O_SQUAD_N_MODERATE_ONE));
        System.out.println();
        testModerateOne(solutions.get(O_N_MODERATE_ONE));
        System.out.println();
        testModerateSecond(solutions.get(BASIC_MODERATE_SECOND));
    }
```

If you want to update any test case, you can use any specific runner or create own. 
```$java
    public static void main(String[] args) {
            Map<String, Solution> solutions = getSolutions();
            // runAllTests(solutions);

            customRunnableTest(solution.get(solutionName));
        }

    private static void customRunnableTest(Solution<*INPUT_TYPE*, *OUTPUT_TYPE*> solution) {
        PRINTER.print(String.format("Starting test case for %s", solution.getClass().getName()));
        Function<*INPUT_TYPE*, *OUTPUT_TYPE*> function = solution::solve;

        testCase.test(*EXPECTED_VALUE*, *INPUT_VALUE*, function); 
    }
``` 
Or you can use exact solution to get it tested. All solution are located in com.sbilyi.solution.(easy|moderate):
```java
   Solution<int[], int[]> solution = new ONModerateOne();
   int[] result = solution.solve(new int[]{1,2,3,4,5});
   for(int i=0;i < result.length; i++) {
        System.out.println(result[i]);   
    }
   





```
Better implementation would have real tests in junit. Separate independent implementations of each solution, which could be used as is.  
It is done this way because of wish to made it cli supported generic for further solution library.