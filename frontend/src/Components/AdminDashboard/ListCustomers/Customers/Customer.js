import React from "react";
import styled from "styled-components";

const Container = styled.div`
    display: flex;
    align-items: center;
    padding: 1rem 0;
    border-bottom: 1px solid rgba(190, 190, 190, 0.22);
    cursor: pointer;
    background-color: ${({ theme }) => theme.primary};
    min-width: 900px;
    transition: all ease-in-out 300ms;

    &:hover {
        /* box-shadow: 0px 10px 8px -8px rgba(138, 153, 192, 0.6); */
        background-color: ${({ theme }) => theme.secondary};
    }
`;

const Text = styled.h1`
    font-size: 0.8rem;
    font-weight: 500;
    color: ${({ theme }) => theme.textColor};
    margin: 0;
`;


const AccountNo = styled.div`
    width: 30%;
    display: flex;
    align-items: center;
`;

const PropertyText = styled.div`
    display: flex;
    flex-direction: column;
    margin-left: 1rem;
`;


const PropertyStreet = styled(Text)`
    font-size: 0.8rem;
`;
const DateOpened = styled(Text)`
    width: 20%;
`;
const Balance = styled(Text)`
    width: 25%;
`;

const Customer = ({ data }) => {

    return (
        <Container>
            <AccountNo>
                <PropertyText>
                    <PropertyStreet>{data.name}</PropertyStreet>
                </PropertyText>
            </AccountNo>
            <DateOpened>{data.userId}</DateOpened>
            <Balance>{data.email}</Balance>
            <Balance>{data.mobile}</Balance>
        </Container>
    );
};

export default Customer;
