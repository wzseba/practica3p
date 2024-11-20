package ventas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Ventas {

	private Map<String, Integer> ventasPorProducto;

	public Ventas() {
		this.ventasPorProducto = new HashMap<String, Integer>();
	}

	public void leerVentas(String entrada) {

		try (BufferedReader br = new BufferedReader(new FileReader(entrada))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(" ");

				String codProducto = datos[0];
				int cantVendida = Integer.parseInt(datos[1]);

				ventasPorProducto.merge(codProducto, cantVendida, Integer::sum);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void escribirVentasPorProducto(String salida) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(salida))) {

			for (Map.Entry<String, Integer> entry : ventasPorProducto.entrySet()) {
				String codigoDeProducto = entry.getKey();
				int cantidadTotalVendida = entry.getValue();

				bw.write(codigoDeProducto + " --> " + cantidadTotalVendida);
				bw.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {

		Ventas ventas = new Ventas();

		ventas.leerVentas("ventas.in");
		ventas.escribirVentasPorProducto("ventasPorProducto.out");
	}

}
