package models;

import models.utils.AppException;
import models.utils.Hash;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Date;

@Entity
public class ModelEmployee extends Model {

	@Id
    public Long stock_id;
	
	@Constraints.Required
    public Long entity_id;
}
