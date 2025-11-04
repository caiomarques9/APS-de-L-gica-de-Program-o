import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        System.out.println("=== ANÁLISE DE NÚMEROS PRIMOS EM JAVA ===\n");

        int[] numerosTeste = {97, 1000000, 1000003};

        for (int n : numerosTeste) {
            System.out.println("Testando número: " + n);
            
            // --- Força Bruta ---
            long inicio = System.nanoTime();
            boolean resultadoForcaBruta = isPrimoForcaBruta(n);
            long duracaoForcaBruta = System.nanoTime() - inicio;
            System.out.println("Força Bruta -> Primo: " + resultadoForcaBruta + 
                               " | Tempo: " + duracaoForcaBruta + " ns");

            // --- Otimizado ---
            inicio = System.nanoTime();
            boolean resultadoOtimizado = isPrimoOtimizado(n);
            long duracaoOtimizado = System.nanoTime() - inicio;
            System.out.println("Otimizado -> Primo: " + resultadoOtimizado + 
                               " | Tempo: " + duracaoOtimizado + " ns");
            
            System.out.println("--------------------------------------------\n");
        }

        // --- Crivo de Eratóstenes ---
        int limite = 1000000;
        System.out.println("Gerando primos até " + limite + " com Crivo de Eratóstenes...");
        long inicioCrivo = System.nanoTime();
        List<Integer> primos = gerarPrimosComCrivo(limite);
        long duracaoCrivo = System.nanoTime() - inicioCrivo;
        System.out.println("Quantidade de primos encontrados: " + primos.size());
        System.out.println("Tempo Crivo: " + duracaoCrivo + " ns\n");

        System.out.println("=== FIM DA ANÁLISE ===");
    }

    // FASE 1 – Força Bruta
    public static boolean isPrimoForcaBruta(int n) {
        if (n < 2) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // FASE 2 – Otimização com Raiz Quadrada
    public static boolean isPrimoOtimizado(int n) {
        if (n < 2) return false;
        int limite = (int) Math.sqrt(n);
        for (int i = 2; i <= limite; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // FASE 3 – Crivo de Eratóstenes
    public static List<Integer> gerarPrimosComCrivo(int limite) {
        boolean[] crivo = new boolean[limite + 1];
        List<Integer> primos = new ArrayList<>();

        for (int i = 2; i <= limite; i++) {
            crivo[i] = true; // inicialmente, todos são "potencialmente primos"
        }

        for (int p = 2; p * p <= limite; p++) {
            if (crivo[p]) {
                for (int multiplo = p * p; multiplo <= limite; multiplo += p) {
                    crivo[multiplo] = false;
                }
            }
        }

        for (int i = 2; i <= limite; i++) {
            if (crivo[i]) primos.add(i);
        }

        return primos;
    }
}