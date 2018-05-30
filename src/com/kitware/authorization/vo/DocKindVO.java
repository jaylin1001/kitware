package com.kitware.authorization.vo;

public class DocKindVO {
	
		private int doc_kind;
		private String doc_name;
		
		
		public DocKindVO(String doc_name) {
			super();
			this.doc_name = doc_name;
		}
		public DocKindVO() {
			super();
		}
		public DocKindVO(int doc_kind, String doc_name) {
			super();
			this.doc_kind = doc_kind;
			this.doc_name = doc_name;
		}
		public int getDoc_kind() {
			return doc_kind;
		}
		public void setDoc_kind(int doc_kind) {
			this.doc_kind = doc_kind;
		}
		public String getDoc_name() {
			return doc_name;
		}
		public void setDoc_name(String doc_name) {
			this.doc_name = doc_name;
		}
		@Override
		public String toString() {
			return "DocKindVO [doc_kind=" + doc_kind + ", doc_name=" + doc_name + "]";
		}
}

