package Battle;

import Battle.Action.*;
import Battle.Magic.*;

import javax.swing.JList;
import javax.swing.JProgressBar;

import Battle.Player;
import Calcuration.Unit;

public class Battle  {
	public final static String HEAL = "��";
	public final static String BLESS = "�ູ";
	public final static String LOCK = "����";
	public final static String PIERCING = "����";
	public final static String CURSE = "����";
	public final static String FIREBALL = "���̾";
	public final static String LAZER_BEAM = "��������";
	public final static String EXPLOSION = "����";
	public final static String LIFE_DRAIN = "������ �巹��";
	
	public String getMagic(long magic)
	{
		switch((int)magic)
		{
			case Magic.HEAL: return HEAL;
			case Magic.BLESS: return BLESS;
			case Magic.LOCK: return LOCK;
			case Magic.PIERCING: return PIERCING;
			case Magic.CURSE: return CURSE;
			case Magic.FIREBALL: return FIREBALL;
			case Magic.LAZER_BEAM: return LAZER_BEAM;
			case Magic.EXPLOSION: return EXPLOSION;
			case Magic.LIFE_DRAIN: return LIFE_DRAIN;
			default:return null;
		}
	}
	
	public void run(Player p1, Player p2, JList prog, JProgressBar HP, JProgressBar MP) throws InterruptedException
	{
		MagicUtils mu = new MagicUtils();
		
		Player off, def, temp;
		if(p1.getFIRST_ATTACK() > p2.getFIRST_ATTACK())
		{
			off = p1;
			def = p2;
			System.out.println(p1.getName() + " ����\n");
		}
		else
		{
			off = p2;
			def = p1;
			System.out.println(p2.getName() + " ����\n");
		}
		
		MyAction act = new MyAction();
		Unit u = new Unit();
		
		System.out.println(p1.getName() + "- HP : " + u.getUnit(p1.getHP()) + ", ���ݷ� : " + u.getUnit(p1.getATK())
							+ ", ���� : " + u.getUnit(p1.getDEF()) + ", ġ��Ÿ�� : " + p1.getCRITICAL_HIT() / 10 +
							", ���� : " + getMagic(p1.getMAGIC()) + ", ���� ���� : " + p1.getMAGIC_LEVEL());
		
		System.out.println(p2.getName() + "- HP : " + u.getUnit(p2.getHP()) + ", ���ݷ� : " + u.getUnit(p2.getATK())
		+ ", ���� : " + u.getUnit(p2.getDEF()) + ", ġ��Ÿ�� : " + p2.getCRITICAL_HIT() / 10 +
		", ���� : " + getMagic(p2.getMAGIC()) + ", ���� ���� : " + p2.getMAGIC_LEVEL());
		
		System.out.println();
		Thread.sleep(1000);
		
		while(true)
		{
			if(isAlive(def))
			{
				if(off.canUseMagic)
				{
					System.out.println(act.Action(off, def, new UseMagic()));
				}
				else
				{
					System.out.println(off.getName() + "��(��) �̹� �� ������ ����� �� ����.");
				}
				Thread.sleep(1000);
				
				if(isAlive(def))
				{
					if(off.canAttack)
					{
						System.out.println(act.Action(off, def, new Attack()));
					}
					else
					{
						System.out.println(off.getName() + "��(��) �̹� �� ������ �� ����.");
					}
					Thread.sleep(1000);
					if(isAlive(def))
					{
						temp = off;
						off = def;
						def = temp;
						off.Initialize();
					}
					else break;
				}
				else break;
			}
			else break;
		}
		
		System.out.println(off.getName() + "�� �¸�!");
	}
	
	boolean isAlive(Player p)
	{
		if(p.getHP() <= 0)
		{
			return false;
		}
		else return true;
	}
}
