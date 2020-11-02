package game;

public class User {
	private String name;
	private int level;
	private int hp ;
	private int maxHp;
//	private int choice;
	private int attack;
	private int exp;

	public User() {
		this.maxHp = 50;
		setHp(maxHp);
		this.exp = 20;
		this.level = 1;
		if(getName()==null || getName().isEmpty()) {
			setName("プレイヤー");
		}
		setAttack(GameBean.attack[(int)(Math.random()*3)]);
	}

	public String getName() {
		return name;
	}
	public int getLevel() {
		return level;
	}
	public int getHp() {
		return hp;
	}
	public int getMaxHp() {
		return maxHp;
	}
//	public int getChoice() {
//		return choice;
//	}
	public int getAttack() {
		return attack;
	}
	public int getExp() {
		return exp;
	}
	public void setName(String name) {
		if(name!=null) {
			this.name = name;
		}
	}
	public void setLevel(int level) {
		if(getLevel()<level) {
			setAttack(getAttack() + 5);
		}
		this.level = level;
	}
	public void setHp(int hp) {
		if(hp < 0) {
			this.hp = 0;
		}else if(hp>getMaxHp()){
			this.hp = getMaxHp();
		}else {
			this.hp = hp;
		}
	}
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
//	public void setChoice(int choice) {
//		this.choice = choice;
//	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public void setExp(int exp) {
		if(exp<0) {
			while(exp>=0) {
				setLevel(getLevel()+1);
				int nextExp = 20;
				exp += nextExp;  //expはマイナス値なのでnextを足して減少させる
			}
		}
		this.exp = exp;
	}

}
