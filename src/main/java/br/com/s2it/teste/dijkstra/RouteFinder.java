/* The authors of this work have released all rights to it and placed it

 in the public domain under the Creative Commons CC0 1.0 waiver
 (http://creativecommons.org/publicdomain/zero/1.0/).

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

 Retrieved from: http://en.literateprograms.org/Dijkstra's_algorithm_(Java)?oldid=15444
 
 @author Weysler M. S. Silveira - weyslerms@gmail.com
 @version 0.0.1 levaetraz - 12/09/2015
 
 */
package br.com.s2it.teste.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import br.com.s2it.teste.vos.RouteVO;

/**
 * The Class RouteFinder.
 */
public class RouteFinder {

	/**
	 * Calcula caminhos origem.
	 * 
	 * @param origem
	 *            the origem
	 */
	public static void calculaCaminhosOrigem(Point origem) {

		origem.minDistance = 0.;
		PriorityQueue<Point> rotaQueue = new PriorityQueue<Point>();
		rotaQueue.add(origem);

		while (!rotaQueue.isEmpty()) {
			Point rota = rotaQueue.poll();

			// Visit each edge exiting u
			for (Neighbor e : rota.adjacencias) {
				Point v = e.target;
				double weight = e.weight;
				double distanceThroughU = rota.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					rotaQueue.remove(v);

					v.minDistance = distanceThroughU;
					v.anterior = rota;
					rotaQueue.add(v);
				}
			}
		}
	}

	/**
	 * Busca caminho mais curto para.
	 * 
	 * @param alvo
	 *            the alvo
	 * @return the list
	 */
	public static List<Point> buscaCaminhoMaisCurtoPara(Point alvo) {

		List<Point> path = new ArrayList<Point>();
		for (Point rota = alvo; rota != null; rota = rota.anterior)
			path.add(rota);

		Collections.reverse(path);
		return path;
	}

	/**
	 * Instanciar pontos.
	 * 
	 * @param pontosHash
	 *            the pontos hash
	 * @return the list
	 */
	public static List<Point> instanciarPontos(Set<String> pontosHash) {

		List<Point> points = new ArrayList<Point>();

		System.out.println("Map points: " + pontosHash);

		for (String s : pontosHash) {
			Point p = new Point(s);
			points.add(p);
		}

		return points;
	}

	/**
	 * Atribui adjacencias.
	 * 
	 * @param points
	 *            the points
	 * @param routes
	 *            the routes
	 */
	public static void atribuiAdjacencias(List<Point> points, List<RouteVO> routes) {

		for (Point p : points) {

			List<Neighbor> adj = new ArrayList<Neighbor>();

			for (RouteVO r : routes) {
				if (r.getSrc().equals(p.name)) {
					Point destino = obterPonto(r.getDst(), points);
					Neighbor vizinho = new Neighbor(destino, r.getDistance());
					adj.add(vizinho);
				}
			}

			Neighbor[] vArray = new Neighbor[adj.size()];
			p.adjacencias = adj.toArray(vArray);
		}
	}

	/**
	 * Obter ponto.
	 * 
	 * @param name
	 *            the name
	 * @param points
	 *            the points
	 * @return the point
	 */
	public static Point obterPonto(String name, List<Point> points) {

		for (Point p : points) {
			if (p.name.equals(name)) {
				return p;
			}
		}

		return null;
	}

}
