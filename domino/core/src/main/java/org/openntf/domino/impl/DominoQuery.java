package org.openntf.domino.impl;

import java.util.ArrayList;
import java.util.Vector;

import lotus.domino.NotesException;

import org.openntf.domino.Session;
import org.openntf.domino.DocumentCollection;
import org.openntf.domino.WrapperFactory;
import org.openntf.domino.utils.DominoUtils;

public class DominoQuery extends BaseThreadSafe<org.openntf.domino.DominoQuery, lotus.domino.DominoQuery, org.openntf.domino.Database>
		implements org.openntf.domino.DominoQuery {

	protected DominoQuery(final lotus.domino.DominoQuery delegate, final org.openntf.domino.Database parent) {
		super(delegate, parent, NOTES_DOMINOQUERY);
	}

	@Override
	public org.openntf.domino.Database getAncestorDatabase() {
		return parent;
	}

	@Override
	public Session getAncestorSession() {
		return parent.getAncestorSession();
	}

	@Override
	public DocumentCollection execute(final String query) {
		try {
			lotus.domino.DocumentCollection lotus = getDelegate().execute(query);
			return fromLotus(lotus, org.openntf.domino.DocumentCollection.SCHEMA, parent);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
			return null;
		}
	}

	@Override
	public String explain(final String query) {
		try {
			return getDelegate().explain(query);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
			return null;
		}
	}

	@Override
	public int getMaxScanDocs() {
		try {
			return getDelegate().getMaxScanDocs();
		} catch (NotesException e) {
			DominoUtils.handleException(e);
			return -1;
		}
	}

	@Override
	public int getMaxScanEntries() {
		try {
			return getDelegate().getMaxScanEntries();
		} catch (NotesException e) {
			DominoUtils.handleException(e);
			return -1;
		}
	}

	@Override
	public int getTimeoutSec() {
		try {
			return getDelegate().getTimeoutSec();
		} catch (NotesException e) {
			DominoUtils.handleException(e);
			return -1;
		}
	}

	@Override
	public boolean isNoViews() {
		try {
			return getDelegate().isNoViews();
		} catch (NotesException e) {
			DominoUtils.handleException(e);
			return false;
		}
	}

	@Override
	public boolean isRefreshViews() {
		try {
			return getDelegate().isRefreshViews();
		} catch (NotesException e) {
			DominoUtils.handleException(e);
			return false;
		}
	}

	@Override
	public String parse(final String query) {
		try {
			return getDelegate().parse(query);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
			return null;
		}
	}

	@Override
	public void resetNamedVariables() {
		try {
			getDelegate().resetNamedVariables();
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}
	}

	@Override
	public void setMaxScanDocs(final int maxScanDocs) {
		try {
			getDelegate().setMaxScanDocs(maxScanDocs);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}
	}

	@Override
	public void setMaxScanEntries(final int maxScanEntries) {
		try {
			getDelegate().setMaxScanEntries(maxScanEntries);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}
	}

	@Override
	public void setNamedVariable(final String varName, final Object value) {
		try {
			getDelegate().setNamedVariable(varName, toDominoFriendly(value, getAncestorSession(), new ArrayList<>()));
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}

	}

	@Override
	public void setNoViews(final boolean noViews) {
		try {
			getDelegate().setNoViews(noViews);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}
	}

	@Override
	public void setRefreshViews(final boolean refreshViews) {
		try {
			getDelegate().setRefreshViews(refreshViews);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}
	}

	@Override
	public void setTimeoutSec(final int timeoutSec) {
		try {
			getDelegate().setTimeoutSec(timeoutSec);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}
	}

	@Override
	protected WrapperFactory getFactory() {
		return parent.getAncestorSession().getFactory();
	}

	@Override
	public boolean isRebuildDesignCatalog() {
		try {
			return getDelegate().isRebuildDesignCatalog();
		} catch (NotesException e) {
			DominoUtils.handleException(e);
			return false;
		}
	}

	@Override
	public void setRebuildDesignCatalog(boolean rebuildCatalog) {
		try {
			getDelegate().setRebuildDesignCatalog(rebuildCatalog);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}
	}

	@Override
	public boolean isRefreshDesignCatalog() {
		try {
			return getDelegate().isRefreshDesignCatalog();
		} catch (NotesException e) {
			DominoUtils.handleException(e);
			return false;
		}
	}

	@Override
	public void setRefreshDesignCatalog(boolean refreshCatalog) {
		try {
			getDelegate().setRefreshDesignCatalog(refreshCatalog);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}
	}

	@Override
	public boolean isRefreshFullText() {
		try {
			return getDelegate().isRefreshFullText();
		} catch (NotesException e) {
			DominoUtils.handleException(e);
			return false;
		}
	}

	@Override
	public void setRefreshFullText(boolean refresh) {
		try {
			getDelegate().setRefreshFullText(refresh);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}
	}

	@Override
	public void createIndex(String name, String field) throws NotesException {
		try {
			getDelegate().createIndex(name, field);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}
	}

	@Override
	public void createIndex(String name, @SuppressWarnings("rawtypes") Vector fields) throws NotesException {
		try {
			getDelegate().createIndex(name, fields);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}
	}

	@Override
	public void createIndex(String name, String field, boolean isVisible, boolean noBuild) throws NotesException {
		try {
			getDelegate().createIndex(name, field, isVisible, noBuild);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}
	}

	@Override
	public void createIndex(String name, @SuppressWarnings("rawtypes") Vector fields, boolean isVisible, boolean noBuild) throws NotesException {
		try {
			getDelegate().createIndex(name, fields, isVisible, noBuild);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}
	}

	@Override
	public String listIndexes() throws NotesException {
		try {
			return getDelegate().listIndexes();
		} catch (NotesException e) {
			DominoUtils.handleException(e);
			return null;
		}
	}

	@Override
	public void removeIndex(String name) throws NotesException {
		try {
			getDelegate().removeIndex(name);
		} catch (NotesException e) {
			DominoUtils.handleException(e);
		}
	}

	
}
