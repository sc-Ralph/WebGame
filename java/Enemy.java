package game;

public class Enemy {
	private String name;
	private int attack;
	private int hp;
	private int maxHp;
	private int exp;

	public Enemy() {
		int n = (int)(Math.random()*3);
		setName(GameBean.enemyName[n]);
		setAttack(GameBean.attack[n]);
		setHp(GameBean.hp[n]);
		setExp(GameBean.exp[n]);
		setMaxHp(getHp());
	}

	public String getName() {
		return this.name;
	}
	public int getAttack() {
		return this.attack;
	}
	public int getHp() {
		return this.hp;
	}
	public int getMaxHp() {
		return this.maxHp;
	}
	public int getExp() {
		return this.exp;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public void setHp(int hp) {
		if(hp<0) {
			this.hp = 0;
		}else {
			this.hp = hp;
		}
	}
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
}
