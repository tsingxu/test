public class note
{
	protected Integer noteID;

	protected String description;

	public Integer getNoteID()
	{
		return noteID;
	}

	public void setNoteID(Integer noteID)
	{
		this.noteID = noteID;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public String toString()
	{
		return "note [noteID=" + noteID + ", description=" + description + "]";
	}

}
