import styled from 'styled-components';

const InputFormStyle = styled.div`
	width: 90%;
	padding: 10px 0;
	font-size: 17px;
	font-weight: bold;
	.textBox {
		display: flex;
		justify-content: space-between;
		align-items: flex-end;
	}
	.inputBox {
		margin-top: 7px;
		border: 1px solid rgb(179, 183, 188);
		height: 30px;
		width: 99%;
		border-radius: 3px;
		background-color: white;
		display: flex;
		align-items: center;
		justify-content: center;

		&:focus-within {
			outline: none;
			border-color: #9ecaed;
			box-shadow: 0 0 10px #9ecaed;
		}
		input {
			width: 95%;
			border: none;
			font-size: 13px;

			&:focus {
				outline: none;
			}
		}
	}
	.blueText {
		color: hsl(206, 100%, 40%);
		font-size: 12px;
	}
`;

const InputForm = ({ text, blueText, type, callback }) => {
	return (
		<InputFormStyle>
			<div className="textBox">
				<div>{text}</div>
				<div className="blueText">{blueText}</div>
			</div>
			<div className="inputBox">
				<input type={type} onChange={callback} />
			</div>
		</InputFormStyle>
	);
};

export default InputForm;
