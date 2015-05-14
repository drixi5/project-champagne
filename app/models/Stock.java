package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Date;

@Entity
public class Stock extends Model {

	@Id
    public Long stock_id;
	
	@Constraints.Required
    public Long entity_id;

    @Constraints.Required
    @Formats.NonEmpty
    @Column(unique = true)
    public String name;

    @Constraints.Required
    @Formats.NonEmpty
    public String type;

    public String unit;

    @Constraints.Required
    @Formats.NonEmpty
    public int quantity;

    @Constraints.Required
    @Formats.NonEmpty
    public int threshold_max;
    
    @Constraints.Required
    @Formats.NonEmpty
    public int threshold_min;
    
    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date dateCreation;

}
