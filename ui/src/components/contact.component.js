import React, { Component } from "react";
import ContactDataService from "../services/contact.service";

export default class Contact extends Component {
  constructor(props) {
    super(props);
    this.onChangeName = this.onChangeName.bind(this);
    this.onChangePhone = this.onChangePhone.bind(this);
    this.getContact = this.getContact.bind(this);
    this.updateContact = this.updateContact.bind(this);
    this.deleteContact = this.deleteContact.bind(this);

    this.state = {
      currentContact: {
        id: null,
        name: "",
        phone: "",
      },
      message: ""
    };
  }

  componentDidMount() {
    this.getContact(this.props.match.params.id);
  }

  onChangeName(e) {
    const name = e.target.value;

    this.setState(function(prevState) {
      return {
        currentContact: {
          ...prevState.currentContact,
          name: name
        }
      };
    });
  }

  onChangePhone(e) {
    const phone = e.target.value;
    
    this.setState(prevState => ({
      currentContact: {
        ...prevState.currentContact,
        phone: phone
      }
    }));
  }

  getContact(id) {
    ContactDataService.get(id)
      .then(response => {
        this.setState({
          currentContact: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }


  updateContact() {
    ContactDataService.update(
      this.state.currentContact.id,
      this.state.currentContact
    )
      .then(response => {
        console.log(response.data);
        this.setState({
          message: "The contact was updated successfully!"
        });
      })
      .catch(e => {
        console.log(e);
      });
  }

  deleteContact() {    
    ContactDataService.delete(this.state.currentContact.id)
      .then(response => {
        console.log(response.data);
        this.props.history.push('/contacts')
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() {
    const { currentContact } = this.state;

    return (
      <div>
        {currentContact ? (
          <div className="edit-form">
            <h4>Contact</h4>
            <form>
              <div className="form-group">
                <label htmlFor="name">Name</label>
                <input
                  type="text"
                  className="form-control"
                  id="name"
                  value={currentContact.name}
                  onChange={this.onChangeName}
                />
              </div>
              <div className="form-group">
                <label htmlFor="phone">Phone Number</label>
                <input
                  type="text"
                  className="form-control"
                  id="phone"
                  value={currentContact.phone}
                  onChange={this.onChangePhone}
                />
              </div>

              <div className="form-group">
                <label>
                  <strong>Status:</strong>
                </label>
              </div>
            </form>

            <button
              className="btn btn-outline-secondary"
              onClick={this.deleteContact}
            >
              Delete
            </button>

            <button
              type="submit"
              className="btn btn-outline-secondary"
              onClick={this.updateContact}
            >
              Update
            </button>
            <p>{this.state.message}</p>
          </div>
        ) : (
          <div>
            <br />
            <p>Please click on a Contact...</p>
          </div>
        )}
      </div>
    );
  }
}
