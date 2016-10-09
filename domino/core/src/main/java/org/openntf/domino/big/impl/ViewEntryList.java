package org.openntf.domino.big.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.openntf.domino.View;
import org.openntf.domino.ViewEntry;
import org.openntf.domino.ViewNavigator;
import org.openntf.domino.big.ViewEntryCoordinate;
import org.openntf.domino.exceptions.UnimplementedException;

public class ViewEntryList implements List<org.openntf.domino.big.ViewEntryCoordinate> {
	private View parentView_;
	private ViewNavigator navigator_;
	protected ViewEntry startEntry_;
	protected ViewEntry stopEntry_;
	protected boolean emptyIterator_ = false;

	public static class ViewEntryListIterator implements ListIterator<ViewEntryCoordinate> {
		private ViewEntryList parentList_;
		private ViewNavigator navigator_;
		private ViewEntry cur_;
		private Boolean hasNextCache_;
		private ViewEntry next_;
		private Boolean hasPrevCache_;
		private ViewEntry prev_;
		private int index_;
		private String stopPosition_;

		ViewEntryListIterator(final ViewNavigator navigator, final ViewEntryList parent) {
			navigator_ = navigator;
			parentList_ = parent;
			if (parentList_.startEntry_ != null) {
				navigator_.gotoEntry(parentList_.startEntry_);
				next_ = navigator_.getCurrent();
				//				System.out.println("Starting entry is in position " + next_.getPosition());
				parentList_.startEntry_ = null;
			}
			if (parentList_.stopEntry_ != null) {
				stopPosition_ = parentList_.stopEntry_.getPosition();
				parentList_.stopEntry_ = null;
			}
		}

		@Override
		public void add(final ViewEntryCoordinate arg0) {
			throw new UnsupportedOperationException("ViewEntryListIterator is not modifiable");
		}

		@Override
		public boolean hasNext() {
			if (parentList_.emptyIterator_) {
				parentList_.emptyIterator_ = false;
				return false;
			}
			if (stopPosition_ != null && cur_ != null) {
				//				System.out.println("Current position is " + cur_.getPosition());
				if (stopPosition_.equals(cur_.getPosition())) {
					return false;
				}
			}
			if (hasNextCache_ == null) {
				if (cur_ == null) {
					if (navigator_.getCount() == 0) {
						hasNextCache_ = false;
					} else {
						hasNextCache_ = true;
					}
				} else {
					next_ = navigator_.getNextSibling(cur_);
					hasNextCache_ = (next_ != null);
				}
			}
			return hasNextCache_;
		}

		@Override
		public boolean hasPrevious() {
			if (hasPrevCache_ == null) {
				if (cur_ == null) {
					if (navigator_.getCount() == 0) {
						hasPrevCache_ = false;
					} else {
						hasPrevCache_ = true;
					}
				} else {
					prev_ = navigator_.getPrevSibling(cur_);
					hasPrevCache_ = prev_ != null;
				}
			}
			return hasPrevCache_;
		}

		@Override
		public ViewEntryCoordinate next() {
			hasPrevCache_ = null;
			hasNextCache_ = null;
			prev_ = cur_;
			if (cur_ == null && next_ != null) {
				cur_ = next_;
			}
			if (cur_ == null) {
				ViewEntry newEntry = navigator_.getFirst();
				cur_ = newEntry;
			} else if (next_ == null) {
				ViewEntry newEntry = navigator_.getNextSibling(cur_);
				cur_ = newEntry;
			} else {
				cur_ = next_;
			}
			next_ = null;
			index_++;
			if (cur_ != null) {
				ViewEntryCoordinate vec = new org.openntf.domino.big.impl.ViewEntryCoordinate(cur_);
				//				System.out.println("TEMP DEBUG current entry position: " + cur_.getPosition() + " id: " + cur_.getUniversalID() + " vec "
				//						+ vec.getUNID());
				vec.setSourceNav(navigator_);
				return vec;
			} else {
				return null;
			}
		}

		@Override
		public int nextIndex() {
			if (hasNext()) {
				return index_ + 1;
			}
			return index_;
		}

		@Override
		public ViewEntryCoordinate previous() {
			hasPrevCache_ = null;
			hasNextCache_ = null;
			next_ = cur_;
			if (prev_ == null) {
				ViewEntry newEntry = navigator_.getPrevSibling(cur_);
				cur_ = newEntry;
			} else {
				cur_ = prev_;
			}
			prev_ = null;
			index_--;
			ViewEntryCoordinate vec = new org.openntf.domino.big.impl.ViewEntryCoordinate(cur_);
			vec.setSourceNav(navigator_);
			return vec;
		}

		@Override
		public int previousIndex() {
			if (hasPrevious()) {
				return index_ - 1;
			}
			return index_;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("ViewEntryListIterator is not modifiable");
		}

		@Override
		public void set(final ViewEntryCoordinate arg0) {
			throw new UnsupportedOperationException("ViewEntryListIterator is not modifiable");
		}

	}

	public ViewEntryList(final ViewNavigator navigator) {
		navigator_ = navigator;
		parentView_ = navigator.getParentView();
	}

	public ViewNavigator getNavigator() {
		return navigator_;
	}

	@Override
	public boolean add(final ViewEntryCoordinate arg0) {
		throw new UnsupportedOperationException("ViewEntryList is not modifiable");
	}

	@Override
	public void add(final int arg0, final ViewEntryCoordinate arg1) {
		throw new UnsupportedOperationException("ViewEntryList is not modifiable");
	}

	@Override
	public boolean addAll(final Collection<? extends ViewEntryCoordinate> arg0) {
		throw new UnsupportedOperationException("ViewEntryList is not modifiable");
	}

	@Override
	public boolean addAll(final int arg0, final Collection<? extends ViewEntryCoordinate> arg1) {
		throw new UnsupportedOperationException("ViewEntryList is not modifiable");
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("ViewEntryList is not modifiable");
	}

	@Override
	public boolean contains(final Object arg0) {
		if (arg0 instanceof ViewEntryCoordinate) {
			ViewEntryCoordinate vec = (ViewEntryCoordinate) arg0;
			ViewEntry current = navigator_.getCurrent();
			boolean result = navigator_.gotoPos(vec.getPosition(), org.openntf.domino.ViewNavigator.DEFAULT_SEPARATOR);
			navigator_.gotoEntry(current);
			return result;
		}
		if (arg0 instanceof ViewEntry) {
			ViewEntry ve = (ViewEntry) arg0;
			ViewEntry current = navigator_.getCurrent();
			boolean result = navigator_.gotoEntry(ve);
			navigator_.gotoEntry(current);
			return result;
		}
		return false;
	}

	@Override
	public boolean containsAll(final Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ViewEntryCoordinate get(final int arg0) {
		ViewEntry entry = getNavigator().getNth(arg0);
		ViewEntryCoordinate vec = new org.openntf.domino.big.impl.ViewEntryCoordinate(entry);
		vec.setSourceNav(getNavigator());
		return vec;
	}

	@Override
	public int indexOf(final Object arg0) {
		throw new UnimplementedException();
	}

	private Boolean isEmpty_ = null;

	@Override
	public boolean isEmpty() {
		if (isEmpty_ == null) {
			isEmpty_ = navigator_.getCount() > 0;
		}
		return isEmpty_;
	}

	@Override
	public Iterator<org.openntf.domino.big.ViewEntryCoordinate> iterator() {
		return new ViewEntryListIterator(navigator_, this);
	}

	@Override
	public int lastIndexOf(final Object arg0) {
		throw new UnimplementedException();
	}

	@Override
	public ListIterator<org.openntf.domino.big.ViewEntryCoordinate> listIterator() {
		return new ViewEntryListIterator(navigator_, this);

	}

	@Override
	public ListIterator<org.openntf.domino.big.ViewEntryCoordinate> listIterator(final int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ViewEntryCoordinate remove(final int arg0) {
		throw new UnsupportedOperationException("ViewEntryList is not modifiable");
	}

	@Override
	public boolean remove(final Object arg0) {
		throw new UnsupportedOperationException("ViewEntryList is not modifiable");
	}

	@Override
	public boolean removeAll(final Collection<?> arg0) {
		throw new UnsupportedOperationException("ViewEntryList is not modifiable");
	}

	@Override
	public boolean retainAll(final Collection<?> arg0) {
		throw new UnsupportedOperationException("ViewEntryList is not modifiable");
	}

	@Override
	public ViewEntryCoordinate set(final int arg0, final ViewEntryCoordinate arg1) {
		throw new UnsupportedOperationException("ViewEntryList is not modifiable");
	}

	@Override
	public int size() {
		return navigator_.getCount();
	}

	@Override
	public List<ViewEntryCoordinate> subList(final int arg0, final int arg1) {
		//		System.out.println("Getting entry from " + arg0 + " position");
		if (arg0 >= size()) {
			emptyIterator_ = true;
		} else {
			startEntry_ = navigator_.getNth(arg0);
		}
		//		System.out.println("Entry position is " + startEntry_.getPosition());
		//		navigator_.gotoEntry(startEntry);
		if (arg1 > 0) {
			if (arg1 > size()) {
				stopEntry_ = navigator_.getNth(size());
			} else {
				stopEntry_ = navigator_.getNth(arg1);
			}
		}
		return this;
	}

	@Override
	public Object[] toArray() {
		throw new UnimplementedException();
	}

	@Override
	public <T> T[] toArray(final T[] arg0) {
		throw new UnimplementedException();
	}

}
