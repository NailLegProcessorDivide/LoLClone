package game;

public class ItemStack {
	private IItem item;
	private int amount;
	
	public ItemStack() {
		amount = 0;
		item = null;
	}
	
	public boolean addItem(IItem item) {
		if(this.item == null || (item.equals(this.item) && (item.getMaxStack()>amount))) {
			this.item = item;
			amount+=1;
			return true;
		}
		return false;
	}
	
	//
	public int addItems(ItemStack items) {
		if(items.getItem() == null) {
			return 0;
		}
		if(this.item == null || (items.getItem().equals(this.item) && (item.getMaxStack()>=amount))) {
			int totalItems = amount + items.getAmount();
			amount = Math.min(item.getMaxStack(), totalItems);
			int originalItems = items.getAmount();
			items.setAmount(totalItems-amount);
			this.item = items.getItem();
			if(items.getAmount() == 0) {
				items.setItem(null);
			}
			
			return originalItems-items.getAmount();
		}
		return 0;
	}

	public IItem getItem() {
		return item;
	}

	public int getAmount() {
		return amount;
	}

	public void setItem(IItem item) {
		this.item = item;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
