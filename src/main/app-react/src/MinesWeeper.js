import React, { useDebugValue } from 'react';
import GridCell from './GridCell';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import History from './Navigation/History';
import {ROUTES} from './Utils/constants';

export class MinesWeeper extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      items: [], text: '', rows: 5, columns: 5, data: [],
      games: [], formDialogVisible: false, newGameRequestRows: 4, newGameRequestCols: 4, newGameRequestMined: 3
    };
  }

  async componentDidMount() {
    this.retrieve();
  }

  async retrieve() {
    const response = await fetch('http://localhost:18888/games/listsaved');
    const json = await response.json();
    console.log('-> ' + json);
    this.setState({ games: json });
  }

  async removeItem(id) {
    console.log('Removing ' + id);

    const response = await fetch('http://localhost:18888/games/' + id, {
      method: 'DELETE', // *GET, POST, PUT, DELETE, etc.
      mode: 'cors', // no-cors, *cors, same-origin
      cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
      credentials: 'same-origin', // include, *same-origin, omit
      headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      redirect: 'follow', // manual, *follow, error
      referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
      body: ''
    });

    this.retrieve();
  }

  resume(id) {
    console.log('asked to resume game with id: ' + id);
    History.replace(ROUTES.PLAY, {gameId: id});
  }

  actionTemplate(rowData, column) {
    console.log('column', column);
    console.log('rowData', rowData);
    return (<div>
      <Button
        type="button" icon="fa-search"
        className="ui-button-success" onClick={() => this.resume(rowData.id)}
      >Resume</Button>
      <Button
        type="button" icon="fa-search"
        className="ui-button-success" onClick={() => this.removeItem(rowData.id)}
      >X</Button>
    </div>);
  }

  render() {
    const headerCard = <div style={{ textAlign: "center" }}><h1>{"Some title"}</h1></div>;
    const headerDatatable =
      <div className="p-grid">
        <Button tooltip={"New game"}
          tooltipOptions={{ position: 'top' }} type="button"
          style={{ marginRight: '.5em' }}
          onClick={(e) => { this.setState({ formDialogVisible: true }); }} >New game</Button>
      </div>;
    return (
      <div>
        <div className="p-grid">
          <div className="p-col-12">
            <Card header={headerCard} style={{ padding: "1em" }}>
              <div className="p-grid">
                <div className="p-col-12">
                  <DataTable value={this.state.games} paginator={false} rows={10}
                    responsive={true} emptyMessage={"No results"} lazy={false}
                    header={headerDatatable}>
                    <Column field="id" header={"Id"} filter={true} />
                    <Column field="createdOn" header={"CreatedOn"} filter={true} />
                    <Column field="player" header={"Player"} filter={true} />
                    <Column field="startedOn" header={"StartedOn"} filter={true} />
                    <Column field="gameStatus" header={"Status"} filter={true} />
                    <Column body={this.actionTemplate.bind(this)} header="Actions" style={{ textAlign: 'center', width: '6em' }} />
                  </DataTable>
                </div>
              </div>
            </Card>
          </div>
        </div>

        <form onSubmit={this.validarRegistro}>
          <Dialog position="center" visible={this.state.formDialogVisible} blockScroll={true}
            style={{ width: '50vw' }} modal={true} onHide={() => this.setState({ formDialogVisible: false })}>
            <div className="p-fluid">
              <div className="p-field p-grid">
                <label htmlFor="newGameRequestRows" className="p-col-12 p-md-2">Rows:</label>
                <div className="p-col-12 p-md-10">
                  <InputText id="newGameRequestRows" type="text" required={true} defaultValue={this.state.newGameRequestRows} onChange={ (e) => {this.setState({newGameRequestRows : e.target.value})} } />
                </div>

                <label htmlFor="newGameRequestCols" className="p-col-12 p-md-2">Cols:</label>
                <div className="p-col-12 p-md-10">
                  <InputText id="newGameRequestCols" type="text" required={true} defaultValue={this.state.newGameRequestCols} onChange={ (e) => {this.setState({newGameRequestCols : e.target.value})} } />
                </div>

                <label htmlFor="newGameRequestMined" className="p-col-12 p-md-2">Cols.,..M:</label>
                <div className="p-col-12 p-md-10">
                  <InputText id="newGameRequestMined" type="text" required={true} defaultValue={this.state.newGameRequestMined} onChange={ (e) => {this.setState({newGameRequestMined : e.target.value})} } />
                </div>

                <label htmlFor="newGameRequestBtn" className="p-col-12 p-md-2">Cols:</label>
                <div className="p-col-12 p-md-10">
                  <Button id="newGameRequestBtn" type="text" required={true} onClick={() => { this.requestNewGame() }} >Create</Button>
                </div>
              </div>
            </div>
            <div>
            </div>
          </Dialog>
        </form>
      </div>
    );
  }

  /**
   * <p>
   * User requests a new Game.
   * </p>
   * @param {*} rowData 
   */
  async requestNewGame() {
    const newGameRequest = {
      rows: this.state.newGameRequestRows, columns: this.state.newGameRequestCols, minedCells: this.state.newGameRequestMined
    }

    console.log(JSON.stringify(this.newGameRequest));
    const response = await fetch('http://localhost:18888/games/new', {
      method: 'POST', // *GET, POST, PUT, DELETE, etc.
      mode: 'cors', // no-cors, *cors, same-origin
      cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
      credentials: 'same-origin', // include, *same-origin, omit
      headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      redirect: 'follow', // manual, *follow, error
      referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
      body: JSON.stringify(newGameRequest) // body data type must match "Content-Type" heade
    });

    this.retrieve();
    return response.json(); // parses JSON response into native JavaScript objects
  }

  /**
    * Validar el registro del usuario.
    * @param {React.FormEvent} event Evento submit del formulario.
    */
  validate(event) {
    // todo

  }
}
