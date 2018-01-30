import java.util.Arrays;

public class NQueens {

	private int n;
	private int[][] board;

	public NQueens(int n) {
		this.n = n;
		board = new int[n][n];
	}

	public boolean solve(int r) {
		if (r >= n) {
			return isValid();
		} else {
			for (int c = 0; c < n; ++c) {
				board[r][c] = 1;
				if (isValid() && solve(r + 1)) {
					return true;
				}
				board[r][c] = 0;
			}
			return false;
		}
	}

	private boolean maxOne(int[] a) {
		return Arrays.stream(a).allMatch(x -> x <= 1);
	}

	private boolean isValid() {
		int[] udiag = new int[2 * n - 1];
		int[] ddiag = new int[2 * n - 1];
		int[] col = new int[n];
		for (int r = 0; r < n; ++r) {
			for (int c = 0; c < n; ++c) {
				udiag[r + c] += board[r][c];
				ddiag[r - c + n - 1] += board[r][c];
				col[c] += board[r][c];
			}
		}
		return maxOne(udiag) && maxOne(ddiag) && maxOne(col);
	}

	public void print() {
		for (int r = 0; r < n; ++r) {
			for (int c = 0; c < n; ++c) {
				System.out.print(board[r][c] == 1 ? '*' : '.');
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		long startTime = System.nanoTime();

		NQueens q = new NQueens(25);
		q.solve(0);
		System.out.println(q.isValid());
		q.print();

		long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
		System.out.println((double) elapsedTime / 1e9);
	}

}
