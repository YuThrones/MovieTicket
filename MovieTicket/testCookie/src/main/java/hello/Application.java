package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private CinemaRepository cinemaRepository;
	
	

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		initCinema();
		initUser();
		initMovie();
		
//		for (Movie movie:movieRepository.findAll()) {
//			System.out.println(movie.getMovieName());
//		}
		
	}
	
	//删除所有用户
	private void initUser() {
		userRepository.deleteAll();
	}
	
	
	//初始化电影数据库，加入8部电影
	private void initMovie() {
		movieRepository.deleteAll();
	    
		addMovie("X战警：天启",
				"2016-06-03",
				"144分钟/英语",
				"布莱恩·辛格/詹妮弗·劳伦斯 / 迈克尔·法斯宾德 / 尼古拉斯·霍尔特 / 詹姆斯·麦卡沃伊 / 查宁·塔图姆 / 埃文·彼得斯 / 休·杰克曼 / 奥斯卡·伊萨克 / 苏菲·特纳 / 泰伊·谢里丹 / 萝丝·拜恩",
				"天启出生于埃及第一王朝，是个弃婴，被沙漠中的一批游荡者收养，饱受“适者生存”理论教育，在残酷的战斗中长大，收养天启的头目似乎听说过某种预言，认定这个小子将成为统治者，当时埃及的法老其实是来自30世纪的穿越者Kang，他也知道天启的潜力，打算控制他，但没有成功就被迫离开了。后来的几千年中，天启游荡人间，发动战争以测试什么国家民族是“适者”。这个过程中，他发现了一些外星科技，进一步强化自己，到了20世纪，天启认为超能力者与普通人类不可共存，因此他坚决反对“X战警”这种支持“和平共处”的理想主义者，拥有不死之身的第一位变种人天启，率四骑士归来，企图重整变种人与人类的明天。",
				"动作  科幻  冒险",
				"/img/movie/xMen.jpg"
				);
		
		addMovie("爱丽丝梦游仙境2：镜中奇遇记",
				"2016-05-27",
				"108分钟/英语",
				"詹姆斯·波宾/约翰尼·德普 / 安妮·海瑟薇 / 米娅·华希科沃斯卡 / 海伦娜·伯翰·卡特 / 麦克·辛 / 艾伦·瑞克曼 / 萨莎·拜伦·科恩 / 瑞斯·伊凡斯 / 爱德华·斯皮伊尔斯 / 托比·琼斯 / 斯蒂芬·弗雷",
				"爱丽丝（米娅·华希科沃斯卡饰）在过去的几年里跟随父亲去海上航行，返回伦敦后她遇到了一面神奇的镜子，穿过镜子她再一次回到了奇幻仙境，并且遇到了昔日的朋友白兔、毛毛虫、疯帽匠（约翰尼·德普饰）等，但此时疯帽匠已不是原来的他了。疯帽匠病入膏肓，整个仙境都面临着被毁灭的威胁，这一切的罪魁祸首就是大反派时间（萨莎·拜伦·科恩饰），只有打败他，才能让仙境恢复往日的繁荣。白皇后（安妮·海瑟薇饰）帮助爱丽丝通过大时钟内部的时空传送仪回到过去，在不同的点她遇到了之前的朋友和敌人，在那里她必须与时间对抗，在时间耗尽之前拯救疯帽匠。",
				"家庭  冒险  奇幻",
				"/img/movie/Alice.jpg"
				);
		
		addMovie("魔兽",
				"2016-06-08",
				"124分钟/英语",
				"邓肯·琼斯/崔维斯·费米尔 / 本·福斯特 / 托比·凯贝尔 / 宝拉·巴顿 / 多米尼克·库珀 / 鲁丝·内伽 / 克兰西·布朗 / 丹尼尔·库德摩尔 / 罗伯特·卡辛斯基 / 本·施耐泽",
				"艾泽拉斯与德拉诺本是两颗祥和安宁的星球，人类在艾泽拉斯大陆上世代繁衍生息，兽人则在德拉诺的土地上辛勤耕耘。直到古尔丹的出现，打破了两个星球的和平。邪恶的古尔丹为了一己私利，使用恶魔能量打开了一扇传送门——黑暗之门，并残忍的破坏兽人的家园。霜狼氏族的酋长杜隆坦不得不带领着自己的族人离开支离破碎的德拉诺，在举步维艰的艾泽拉斯大陆上寻找着庇护所。面临家园危机的人类在领袖洛萨的带领下一边痛击着野蛮的入侵者，一边苦苦寻求着战争之外的解救方法。两个种族面临着同样的危机，他们必须设法渡过这场灾难。",
				"动作  魔幻  冒险  战争",
				"/img/movie/moshou.jpg"
				);
		
		addMovie("分歧者3：忠诚世界",
				"2016-05-20",
				"121分钟/英语",
				"罗伯特·斯文克/谢琳·伍德蕾 / 提奥·詹姆斯 / 迈尔斯·特勒 / 安塞尔·艾尔高特 / 娜奥米·沃茨 / 杰夫·丹尼尔斯 / 比尔·斯卡斯加德",
				"派系制度瓦解后，分歧者翠丝（谢琳·伍德蕾饰）揭开了整个人类的秘密，原来芝加哥不过是美国基因局制造的一场实验。一片混乱之下的芝加哥危机四伏，老四的母亲伊芙琳（娜奥米·沃茨饰）组织了强有力的武装力量，企图占领芝加哥成为新的统治者。翠丝决定带领老四（提奥·詹姆斯饰）、克里斯蒂娜（佐伊·克拉维兹饰）、皮特（迈尔斯·特勒饰）、托莉（李美琪饰）和凯莱布（安塞尔·埃尔格特饰）逃往芝加哥城外的世界，为芝加哥人民寻找新的希望。他们历经险阻成功逃脱，终于来到了墙外的神秘世界，充斥着各种高科技技术的基因局。在这个全新的世界里，翠丝和老四的价值观被完全颠覆，二人之间的信任也遭受到前所未有的考验。当他们终于发现了基因局负责人大卫（杰夫·丹尼尔斯饰）所谓的神秘事业背后所掩藏的更大阴谋时，却没有察觉自己其实早已被卷入了其中。为了保护芝加哥的人民，翠丝和老四做出了忠于内心的选择，他们要联合所有派系向墙外世界宣战！他们以卵击石的背水一战究竟能否颠覆世界。",
				"爱情  动作  科幻  冒险",
				"/img/movie/fenqizhe3.jpg"
				);
		
		addMovie("愤怒的小鸟",
				"2016-05-20",
				"97分钟/原版",
				"克莱·凯蒂 / Fergal Reilly/杰森·苏戴奇斯 / 乔什·加德 / 丹尼·麦克布耐德 / 玛娅·鲁道夫 / 西恩·潘 / 凯特·麦克金农 / 彼特·丁克拉奇 / 比尔·哈德尔",
				"在一座与世隔绝的美丽小岛上，住着一群乐天知命的鸟。不过易怒的大红、亢奋的恰克和容易爆炸的炸弹总是因为各自的怪性格而不被其他的鸟接纳。不过当神秘的绿猪造访这座世外小岛，就得由这群怪咖查出这些神秘兮兮的外来客到底有何目的。作为一只天生拥有黑粗眉的小鸟，“胖红”走到哪里都被嘲笑或是无视，它对在家门外踢球的捣蛋鬼毫不客气，暴脾气让它更加孤僻。不过即便是暴躁的“胖红”也有脾气相投的死党，“飞镖黄”与“炸弹黑”就是它的好基友。当“绿猪”初到小鸟岛时，正是敏锐的“胖红”发现了蹊跷之处，然而法官大人却无视“胖红”的忠告，导致蠢萌“绿猪”诡计得逞。",
				"动作  冒险  喜剧",
				"/img/movie/AngryBird.jpg"
				);
		
		addMovie("超脑48小时",
				"2016-05-13",
				"113分钟/国语",
				"阿里尔·弗罗门/瑞恩·雷诺兹 / 凯文·科斯特纳 / 加里·奥德曼 / 汤米·李·琼斯 / 盖尔·加朵 / 爱丽丝·伊芙 / 阿莫里·诺拉斯科 / 安婕·特拉乌",
				"一位CIA探员意外身亡，在知名神经学家（汤米·李·琼斯 Tommy Lee Jones 饰）的协助下，他的记忆与技能被植入一位极度危险且无法预测的罪犯（凯文·科斯特纳 Kevin Costner 饰）身上，而探员（瑞恩·雷诺兹 Ryan Reynolds 饰）必须靠这位不定时炸弹 来完成一项重要的秘密任务。",
				"动作  惊悚  剧情  悬疑  罪案",
				"/img/movie/brain.jpg"
				);
		
		addMovie("幻体：续命游戏",
				"2016-05-12",
				"117分钟/英语",
				"塔西姆·辛/瑞恩·雷诺兹 / 本·金斯利 / 马修·古迪 / 娜塔丽·马丁内兹 / 米歇尔·道克瑞 / 德里克·卢克 / 维克多·加博",
				"曾在纽约建筑界享有极高声誉的富豪马丁·戴米恩（本·金斯利 Ben Kingsley 饰），而今饱受癌症折磨，不得不面对即将在半年后去世的事实。除此之外，他与女儿克莱尔的关系始终处于冰封状态，这令他不仅慨叹金钱的无力。不久前，马丁与菲尼克斯生物科技公司取得联系，对方声称使用最先进的脱换技术可以帮助马丁重获新生，简而言之就是将他的意识或者灵魂注入一具更加年轻健康的躯体内再生。经过慎重的考虑，马丁以爱德华·基德纳（瑞恩·雷诺兹 Ryan Reynolds 饰）的身份复活。此后的一段时间里，爱德华尽情享受重返青春的乐趣。 与此同时，这具陌生躯体背后所隐藏的秘密，则让他渐渐感到不安……",
				"惊悚  科幻  悬疑",
				"/img/movie/huanti.jpg"
				);
		
		addMovie("美国队长3",
				"2016-05-06",
				"147分钟/英语",
				"乔·卢素 / 安东尼·卢素/克里斯·埃文斯 / 小罗伯特·唐尼 / 斯嘉丽·约翰逊 / 塞巴斯蒂安·斯坦 / 杰瑞米·雷纳 / 伊丽莎白·奥尔森 / 查德维克·博斯曼 / 弗兰克·格里罗 / 汤姆·霍兰德 / 丹尼尔·布鲁赫",
				"美国队长史蒂夫·罗杰斯（克里斯·埃文斯 Chris Evans 饰）带领着全新组建的复仇者联盟，继续维护世界和平。然而，一次执行任务时联盟成员不小心造成大量平民伤亡，从而激发政治压力，政府决定通过一套监管系统来管理和领导复仇者联盟。联盟内部因此分裂为两派——一方由史蒂夫·罗杰斯领导，他主张维护成员自由，在免受政府干扰的情况下保护世界；另一方则追随托尼·斯塔克（小罗伯特·唐尼 Robert Downey Jr. 饰），他令人意外地决定支持政府的监管和责任制体系。神秘莫测的巴基（塞巴斯蒂安·斯坦 Sebastian Stan 饰）似乎成为内战的关键人物……",
				"动作  科幻  冒险",
				"/img/movie/America3.jpg"
				);
		
//		for (Cinema oneCinema:cinemaRepository.findAll()) {
//			showCinema(oneCinema);
//		}

	}
	
	//根据电影相关信息往数据库中添加电影
	private void addMovie(String movieName,
			String date,
			String language,
			String actor,
			String movieDescription,
			String type,
			String imgUrl) {
		
		Movie movie0 = new Movie();
		movie0.setMovieName(movieName);
		movie0.setDate(date);
		movie0.setLanguage(language);
		movie0.setActor(actor);
	    movie0.setMovieDescription(movieDescription);
	    movie0.setType(type);
	    movie0.setImgUrl(imgUrl);
	    movieRepository.save(movie0);
	    
	    String[] timeList = {"5月19日","5月20日","5月21日","5月22日"};
	    
	    for (Cinema oneCinema:cinemaRepository.findAll()) {
	    	for (int i = 0; i < 4; i++) {
	    		 List<Screen> screenList = getScreenList(movieName);
	    		 oneCinema.addScreenList(timeList[i], screenList);
	    	}
	    	cinemaRepository.save(oneCinema);
		}
	}
	
	//初始化电影院信息
	private void initCinema() {
		cinemaRepository.deleteAll();
		
		String[] cinemaNameList = {"大光明电影院","三林大光明DFC影城","华谊兄弟影院-长风店","环艺电影城"};
		
		for (int i = 0; i < 4; i++) {
			Cinema cinema = new Cinema();
			cinema.setCinemaName(cinemaNameList[i]);
			cinemaRepository.save(cinema);
		}
		
//		for (Cinema oneCinema:cinemaRepository.findAll()) {
//			showCinema(oneCinema);
//		}
	}
	
	//得到包含这部电影的场次列表
	public List<Screen> getScreenList(String movieName) {
		List<Screen> screenList = new ArrayList<Screen>();
		String[] timeList = {"9:00", "15:00", "21:00"};
		
		for(int i = 0; i < 3; i++) {
			Screen screen = new Screen();
			screen.setMovieName(movieName);
			screen.setPrice(66);
			screen.setRoom("1号厅");
			screen.setTime(timeList[i]);
		    screenList.add(screen);
		}
		
		return screenList;
	}
	
	//展示所有电影院中包含的信息，仅用于测试
	public void showCinema(Cinema cinema) {
		System.out.println("Cinema Name: " + cinema.getCinemaName());
		Map<String, List<Screen>> screenMap = cinema.getScreenMap();
		Set<String> timeSet = screenMap.keySet();
		for (String time:timeSet) {
			System.out.println("  Time: " + time);
			for (Screen screen:screenMap.get(time)) {
				System.out.println("    MovieName: " + screen.getMovieName());
				System.out.println("    Price: " + screen.getPrice());
				System.out.println("    Roome: " + screen.getRoom());
				System.out.println("    Time: " + screen.getTime());
			}
		}
	}
}
