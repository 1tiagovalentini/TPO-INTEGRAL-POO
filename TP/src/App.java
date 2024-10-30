import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Ingrese el número de la acción a realizar: \n" +
        "1.Crear un nuevo evento\n" + 
        "2.Modificar un evento\n" +
        "3.Ver eventos\n" + 
        "4.Ver registro de personas de cierto evento\n" +
        "5.Inscribir una persona a un evento" +
        "6.Gestionar recursos" +
        "7.Ver calendario");

        
        int opcion;
        Scanner input = new Scanner(System.in);

        try{
            do {
                System.out.println("Ingrese numero:");
                opcion = Integer.parseInt(input.nextLine());
            } while (opcion < 1 || opcion > 5);
        
        }
        finally{
            input.close();
        }
    }
}
