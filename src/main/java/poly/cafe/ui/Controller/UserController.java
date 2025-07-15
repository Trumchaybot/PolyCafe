/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.cafe.ui.Controller;

import poly.cafe.ui.manager.controller.CrudController;
import poly.cafe.entity.User;

/**
 *
 * @author truog
 */
public interface UserController extends CrudController<User>{
    void chooseFile();
}
