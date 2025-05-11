package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


@SpringBootApplication
@Validated
public class DemoApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		RecipeService recipeService = context.getBean(RecipeService.class);
        UserService userService = context.getBean(UserService.class);
        Scanner cin = new Scanner(System.in);
        Scanner br = new Scanner(System.in);

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("\nWelcome:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice1 = Integer.parseInt(br.nextLine());
            switch (choice1) {
                case 1:
                    System.out.print("Username: ");
                    String regUser = br.nextLine();
                    System.out.print("Password: ");
                    String regPass = br.nextLine();
                    UserDTO user = new UserDTO(regUser, regPass);
                    userService.register(user);
                    continue;
                case 2:
                    System.out.print("Username: ");
                    String loginUser = br.nextLine();
                    System.out.print("Password: ");
                    String loginPass = br.nextLine();
                    UserDTO usr = new UserDTO(loginUser, loginPass);
                    if (!userService.authenticate(usr)) {
                        System.out.println("no such user or already found\n");
                        continue;
                    }
                    break;
                case 3:
                    continue;
            }
            loggedIn = true;
        }
        
        int x;

        do {
            System.out.println("exit 0" + "1:Get all recipes\n" + "2:Create recipe\n" + "3:update Recipe\n" + "4:Get a recipe by its id\n" +
                    "5:Delete a recipe\n" + "6:Search for recipes by title\n" + "7:Search for recipes by category\n"
                    + "8:Search for recipes by cooking time\n" + "9:Search for recipes by ingredient\n" +
                    "10:Search for recipes by instruction\n" + "11:give a sorted page"
            );
            System.out.println("Enter what you want to do\n");
            x = cin.nextInt();
            cin.nextLine();
            switch (x) {

                case 1:
                    recipeService.display(recipeService.getAllRecipes());
                    break;

                case 2:
                    String a;
                    System.out.println("Enter the title");
                    a = br.nextLine();
                    String b;
                    System.out.println("Enter the category");
                    b = br.nextLine();
                    int d;
                    System.out.println("enter the cooking time");
                    d = cin.nextInt();
                    String c;
                    System.out.println("Enter the instructions");
                    c = br.nextLine();
                    String e;
                    System.out.println("Enter the instruction");
                    e = br.nextLine();
                    Recipe recipe = new Recipe(a, d, b, c, e);
                    recipeService.createRecipe(recipe);
                    break;

                case 3:
                    System.out.println("Enter the id of the recipe you want to update");
                    String l;
                    l = br.nextLine();
                    Optional<Recipe> recipe2 = recipeService.getById(l);
                    if (recipe2.isEmpty()) {
                        break;
                    } else {
                        String j;
                        System.out.println("Enter the new title");
                        j = br.nextLine();
                        String k;
                        System.out.println("Enter the new category");
                        k = br.nextLine();
                        System.out.println("Enter the new cooking time");
                        int z;
                        z = cin.nextInt();
                        System.out.println("Enter the new ingredients");
                        String m;
                        m = br.nextLine();
                        System.out.println("Enter the new instructions ");
                        String inst;
                        inst = br.nextLine();
                        Recipe recipe1 = new Recipe(j, z, k, m, inst);
                        recipeService.updateSpecificRecipe(l, recipe1);
                        break;
                    }


                case 4:
                    System.out.println("Enter the id you want to search for");
                    String q;
                    q = br.nextLine();
                    Optional<Recipe> recipe3 = recipeService.getById(q);
                    if (recipe3.isEmpty()) {
                        break;
                    } else {
                        recipeService.display_recipe(recipe3.get());
                        break;
                    }

                case 5:
                    System.out.println("Enter the id of the recipe you want to delete");
                    String r;
                    r = br.nextLine();
                    recipeService.deleteById(r);
                    break;

                case 6:
                    System.out.println("Enter the title you want to search for");
                    String w;
                    w = br.nextLine();
                    Optional<Recipe> recipe4 = recipeService.searchByTitle(w);
                    if (recipe4.isEmpty()) {
                        break;
                    } else {
                        recipeService.display_recipe(recipe4.get());
                        break;
                    }

                case 7:
                    System.out.println("Enter the category you want to search for");
                    String g;
                    g = br.nextLine();
                    List<Recipe> recipe7 = recipeService.searchByCategory(g);
                    if (recipe7.isEmpty()) {
                        break;
                    } else {
                        recipeService.display(recipe7);
                        break;
                    }

                case 8:
                    System.out.println("Enter the cooking time you want to search for");
                    int u;
                    u = cin.nextInt();
                    List<Recipe> recipe8 = recipeService.searchByCookingTime(u);
                    if (recipe8.isEmpty()) {
                        break;
                    } else {
                        recipeService.display(recipe8);
                        break;
                    }

                case 9:
                    System.out.println("Enter the ingredient you want to search for");
                    String h;
                    h = br.nextLine();
                    List<Recipe> recipe9 = recipeService.searchByIngredients(h);
                    if (recipe9.isEmpty()) {
                        break;
                    } else {
                        recipeService.display(recipe9);
                        break;

                    }


                case 10:
                    System.out.println("enter the instruction you want to search for");
                    String o;
                    o = br.nextLine();
                    recipeService.display(recipeService.searchByInstructions(o));
                    break;

                case 11:
                    System.out.println("Enter the nb page you want :");
                    int nb_page = cin.nextInt();
                    System.out.println("Enter the number of recipes per page:");
                    int number = cin.nextInt();
                    System.out.println("Enter the element from the recipes you want to sort by :");
                    String element = br.nextLine();
                    Page<Recipe> p = recipeService.getSortedRecipes(nb_page, number, element);
                    p.forEach(System.out::println);
                    break;

            }

        } while (x != 0);
    }
}


	/*public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class);
		RecipeService recipeService = context.getBean(RecipeService.class);
		recipeService.deleteById("681d2a5d5749280f33b90cea");
	}
	s@Bean
	CommandLineRunner runner(RecipeRepository repository, MongoTemplate mongoTemplate,RecipeService recipeService,RecipeController recipeController){
		return args->{
			Recipe recipe = new Recipe("fatoush",120,"main course", "Pasta","Pasta");
			String khara = "fatoush";
			recipeController.getById("68195907b89b415041d99ba1");
			recipeService.getById("68195907b89b415041d99ba1");
			Query query = new Query();
			query.addCriteria(Criteria.where("title").is(khara));
			List<Recipe> recipes = mongoTemplate.find(query, Recipe.class);
			if (recipes.size() > 1)
			{
				System.out.println("la2a ktir");
			}
			if (recipes.isEmpty())
			{
				System.out.println("inserting student");
				repository.insert(recipe);
			}
			else {
				System.out.println(recipe + " already exists");
			}
		};

    }*/

