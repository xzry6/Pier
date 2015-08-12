package crawler.ranking;

public class ReaderFactory {
	public static BasicReader readCollegeRanking() {
		return new CollegeRankingReader();
	}
	public static BasicReader readMajor() {
		return new MajorReader();
	}
}
