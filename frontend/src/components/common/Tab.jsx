import { useState } from 'react';
import styled from 'styled-components';
const TabStyle = styled.div`
	border: 1px solid rgb(179, 183, 188);
	border-radius: 3px;
	background-color: white;
	display: flex;
	justify-content: center;
	align-items: center;

	button {
		padding: 10px;
		width: 100%;
		height: 100%;
		background-color: white;

		&:hover {
			filter: brightness(90%);
		}
	}
	.non-last {
		border-right: 1px solid rgb(179, 183, 188);
	}
	.active {
		background-color: rgb(228, 230, 232);
	}
`;
const Tab = ({ tabList, tab, setTab }) => {
	return (
		<TabStyle>
			{tabList.map((ele, idx) => {
				return (
					<button
						key={idx}
						className={
							tabList.length - 1 !== idx
								? tab === ele
									? 'active non-last'
									: 'non-last'
								: tab === ele
								? 'active'
								: ''
						}
						onClick={() => setTab(ele)}>
						{ele}
					</button>
				);
			})}
		</TabStyle>
	);
};

export default Tab;
