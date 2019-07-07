package com.johnson.toDoList;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;





@Entity
public class TodoTask {
	
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String title;
    private String description;
    

	private String createdBy;
    
  
	private boolean status;
    
    public TodoTask() {
    	
    }

    	public TodoTask(String title, String description, String createdBy, boolean status) {
    		this.title = title;
    		this.description = description;
    		this.createdBy = createdBy;
    		this.status = false;
    	}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}


		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return "TodoTask [id=" + id + ", title=" + title + ", description=" + description + ", createdBy="
					+ createdBy + ", status=" + status + "]";
		}

    	
}