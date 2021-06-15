import React from 'react';

export default class GridCell extends React.Component {
    render() {
        return (
            <button onClick={this.handleClic}>({this.props.x},{this.props.y})</button>
        );
    }

    handleClic() {
        alert('clicked!');
    }
}